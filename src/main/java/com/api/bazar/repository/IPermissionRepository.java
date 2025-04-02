package com.api.bazar.repository;

import com.api.bazar.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPermissionRepository extends JpaRepository<Permission, Long> {

    Permission findByPermissionName(String name);
}
