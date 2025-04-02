package com.api.bazar.service;

import com.api.bazar.model.UserSec;
import com.api.bazar.repository.IUserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private final IUserRepository userRepo;

    public UserDetailsServiceImp(IUserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSec userSec = userRepo.findUserEntityByUsername(username);
        if (userSec == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        List<GrantedAuthority> authorityList = new ArrayList<>();

        // Agregar roles con prefijo ROLE_
        userSec.getRolesList().forEach(role -> {
            authorityList.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));

            // Agregar permisos si es necesario
            role.getPermissionsList().forEach(permission -> {
                authorityList.add(new SimpleGrantedAuthority(permission.getPermissionName()));
            });
        });

        return new User(
                userSec.getUsername(),
                userSec.getPassword(),
                userSec.isEnabled(),
                userSec.isAccountNotExpired(),
                userSec.isCredentialNotExpired(),
                userSec.isAccountNotLocked(),
                authorityList
        );
    }
}
