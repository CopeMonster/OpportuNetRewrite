package com.windowsxp.opportunetrewrite.controllers;

import com.windowsxp.opportunetrewrite.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
}
