package com.api.bazar.service;

import com.api.bazar.model.Permission;
import com.api.bazar.repository.IPermissionRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PermissionService implements IPermissionService {
    
    private final IPermissionRepository permissionRepository;
    
    public PermissionService(IPermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }
    
    @Override
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }
    
    @Override
    public Permission findById(Long id) {
        return permissionRepository.findById(id).orElse(null);
    }
    
    @Override
    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }
    
    @Override
    public void deleteById(Long id) {
        permissionRepository.deleteById(id);
    }
    
    @Override
    public Permission update(Permission permission) {
        return permissionRepository.save(permission);
    }
}
