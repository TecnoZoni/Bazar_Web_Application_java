package com.api.bazar.service;

import com.api.bazar.model.UserSec;
import java.util.List;

public interface IUserService {

    public List<UserSec> findAll();

    public UserSec findById(Long id);

    public UserSec save(UserSec userSec);

    public void deleteById(Long id);

    public void update(UserSec userSec);

    public String encriptPassword(String password);
}
