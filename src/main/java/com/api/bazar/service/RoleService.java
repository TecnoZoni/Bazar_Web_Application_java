package com.api.bazar.service;

import com.api.bazar.model.Role;
import com.api.bazar.repository.IRoleRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService {

    private final IRoleRepository roleRepository;

    public RoleService(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role update(Role role) {
        return roleRepository.save(role);
    }
}
