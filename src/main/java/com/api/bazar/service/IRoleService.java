package com.api.bazar.service;

import com.api.bazar.model.Role;
import java.util.List;

public interface IRoleService {

    List<Role> findAll();

    Role findById(Long id);

    Role save(Role role);

    void deleteById(Long id);

    Role update(Role role);
}
