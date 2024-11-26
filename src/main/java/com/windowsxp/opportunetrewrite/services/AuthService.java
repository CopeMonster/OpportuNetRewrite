package com.windowsxp.opportunetrewrite.services;

import com.windowsxp.opportunetrewrite.dto.*;
import com.windowsxp.opportunetrewrite.entities.Company;
import com.windowsxp.opportunetrewrite.entities.Student;
import com.windowsxp.opportunetrewrite.exceptions.custom.UserExistException;
import com.windowsxp.opportunetrewrite.exceptions.custom.UserNotFoundException;
import com.windowsxp.opportunetrewrite.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final StudentService studentService;
    private final CompanyService companyService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public JwtResponseDTO login(@RequestBody LogInRequestDTO authRequestDTO) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(authRequestDTO.getEmail(), authRequestDTO.getPassword()));

        if (authentication.isAuthenticated()) {
            return JwtResponseDTO.builder()
                    .accessToken(jwtService.generateToken(authRequestDTO.getEmail()))
                    .build();
        } else {
            throw new UserNotFoundException("Invalid user request");
        }
    }

    public UserSignUpResponse registerStudent(StudentSignUpRequestDTO dto) {
        if (studentService.isStudentExistByEmail(dto.getEmail())) {
            throw new UserExistException("User with email " + dto.getEmail() + " is already exist");
        }

        Student student = Student.builder()
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .phoneNumber(dto.getPhoneNumber())
                .roles(Set.of(
                        roleService.getRoleByName("USER"),
                        roleService.getRoleByName("STUDENT")
                ))
                .dateOfBirth(dto.getDateOfBirth())
                .build();

        studentService.saveStudent(student);

        return UserSignUpResponse.builder()
                .id(student.getId())
                .email(student.getEmail())
                .build();
    }

    public UserSignUpResponse registerCompany(CompanySignUpRequestDTO dto) {
        if (companyService.isCompanyExistByEmail(dto.getEmail())) {
            throw new UserExistException("User with email " + dto.getEmail() + " is already exist");
        }

        Company company = Company.builder()
                .companyName(dto.getCompanyName())
                .email(dto.getEmail())
                .BIN(dto.getBIN())
                .roles(Set.of(
                        roleService.getRoleByName("USER"),
                        roleService.getRoleByName("COMPANY")
                ))
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();

        companyService.saveCompany(company);

        return UserSignUpResponse.builder()
                .id(company.getId())
                .email(company.getEmail())
                .build();
    }
}
