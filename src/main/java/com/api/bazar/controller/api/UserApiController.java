package com.api.bazar.controller.api;

import com.api.bazar.model.Role;
import com.api.bazar.model.UserSec;
import com.api.bazar.service.IRoleService;
import com.api.bazar.service.IUserService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserApiController {

    private final IUserService userService;

    private final IRoleService roleService;

    public UserApiController(IUserService userService, IRoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity getAllUsers() {
        List<UserSec> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable Long id) {
        UserSec user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody UserSec userSec) {

        Set roleList = new HashSet();
        Role readRole;

        //encriptamos contraseña
        userSec.setPassword(userService.encriptPassword(userSec.getPassword()));

        // Recuperar la Permission/s por su ID
        for (Role role : userSec.getRolesList()) {
            readRole = roleService.findById(role.getId());
            if (readRole != null) {
                //si encuentro, guardo en la lista
                roleList.add(readRole);
            }
        }

        if (!roleList.isEmpty()) {
            userSec.setRolesList(roleList);

            UserSec newUser = userService.save(userSec);
            return ResponseEntity.ok(newUser);
        }
        return null;
    }

}
