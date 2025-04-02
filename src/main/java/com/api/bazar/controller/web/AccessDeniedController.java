package com.api.bazar.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccessDeniedController {

    @GetMapping("/admin/access-denied")
    public String accessDenied() {
        return "access_denied";
    }
}
