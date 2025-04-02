package com.api.bazar.config;

import com.api.bazar.model.*;
import com.api.bazar.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final IPermissionRepository permissionRepository;
    private final IRoleRepository roleRepository;
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(IPermissionRepository permissionRepository,
            IRoleRepository roleRepository,
            IUserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.permissionRepository = permissionRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Verificar si ya existe el usuario admin para no duplicar datos
        if (userRepository.findUserEntityByUsername("admin") == null) {
            // 1. Crear permisos básicos (usando merge para evitar el detached entity)
            Set<Permission> permissions = new HashSet<>();
            permissions.add(createOrGetPermission("READ"));
            permissions.add(createOrGetPermission("CREATE"));
            permissions.add(createOrGetPermission("UPDATE"));
            permissions.add(createOrGetPermission("DELETE"));

            // 2. Crear rol ADMIN
            Role adminRole = new Role();
            adminRole.setRole("ADMIN");
            adminRole.setPermissionsList(permissions);
            roleRepository.save(adminRole);

            // 3. Crear usuario administrador
            UserSec adminUser = new UserSec();
            adminUser.setUsername("admin");
            adminUser.setPassword(passwordEncoder.encode("admin"));
            adminUser.setEnabled(true);
            adminUser.setAccountNotExpired(true);
            adminUser.setAccountNotLocked(true);
            adminUser.setCredentialNotExpired(true);

            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);
            adminUser.setRolesList(roles);

            userRepository.save(adminUser);
        }
    }

    private Permission createOrGetPermission(String name) {
        Permission permission = permissionRepository.findByPermissionName(name);
        if (permission == null) {
            permission = new Permission();
            permission.setPermissionName(name);
            permission = permissionRepository.save(permission);
        }
        return permission;
    }
}
