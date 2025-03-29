package com.api.bazar.service;

import com.api.bazar.model.Permission;
import java.util.List;

public interface IPermissionService {

    List<Permission> findAll();

    Permission findById(Long id);

    Permission save(Permission permission);

    void deleteById(Long id);

    Permission update(Permission permission);

}
