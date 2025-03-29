package com.api.bazar.controller.api;

import com.api.bazar.model.Permission;
import com.api.bazar.service.IPermissionService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/permissions")
public class PermissionApiController {

    private final IPermissionService permissionService;

    public PermissionApiController(IPermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping
    public ResponseEntity getAllPermissions() {
        List<Permission> permissions = permissionService.findAll();
        return ResponseEntity.ok(permissions);
    }

    @GetMapping("/{id}")
    public ResponseEntity getPermissionById(@PathVariable Long id) {
        Permission permission = permissionService.findById(id);
        return ResponseEntity.ok(permission);
    }

    @PostMapping
    public ResponseEntity createPermission(@RequestBody Permission permission) {
        Permission newPermission = permissionService.save(permission);
        return ResponseEntity.ok(newPermission);
    }

}
