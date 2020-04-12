package com.sso.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 孔超
 * @date 2020/4/11 22:33
 */
@RestController
public class UserController {

    @GetMapping("/user")
    public Authentication user(@AuthenticationPrincipal Authentication authentication) {
        return authentication;
    }
}
