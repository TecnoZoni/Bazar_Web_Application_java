package com.api.bazar.repository;

import com.api.bazar.model.UserSec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserSec, Long> {

    UserSec findUserEntityByUsername(String username);
}
