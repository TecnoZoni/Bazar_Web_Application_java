package com.api.bazar.service;

import com.api.bazar.model.UserSec;
import com.api.bazar.repository.IUserRepository;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserSec> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserSec findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public UserSec save(UserSec userSec) {
        return userRepository.save(userSec);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void update(UserSec userSec) {
        save(userSec);
    }

    @Override
    public String encriptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
