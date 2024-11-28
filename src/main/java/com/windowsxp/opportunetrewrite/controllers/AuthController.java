package com.windowsxp.opportunetrewrite.controllers;

import com.windowsxp.opportunetrewrite.dto.*;
import com.windowsxp.opportunetrewrite.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<JwtResponseDTO> signIn(@Valid @RequestBody LogInRequestDTO authRequestDTO) {
        return ResponseEntity.ok(authService.login(authRequestDTO));
    }

    @PostMapping("/sign-up/student")
    public ResponseEntity<UserSignUpResponse> studentSignUp(@Valid @RequestBody StudentSignUpRequestDTO dto) {
        return ResponseEntity.ok(authService.registerStudent(dto));
    }

    @PostMapping("/sign-up/company")
    public ResponseEntity<UserSignUpResponse> companySignUp(@Valid @RequestBody CompanySignUpRequestDTO dto) {
        return ResponseEntity.ok(authService.registerCompany(dto));
    }
}
