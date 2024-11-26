package com.windowsxp.opportunetrewrite.config.init;

import com.windowsxp.opportunetrewrite.entities.Admin;
import com.windowsxp.opportunetrewrite.entities.Role;
import com.windowsxp.opportunetrewrite.entities.User;
import com.windowsxp.opportunetrewrite.services.AdminService;
import com.windowsxp.opportunetrewrite.services.RoleService;
import com.windowsxp.opportunetrewrite.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
@Order(2)
public class AdminInitializer implements CommandLineRunner {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final AdminService adminService;

    @Value("${application.admin-email}")
    private String email;

    @Value("${application.admin-password}")
    private String password;

    @Override
    public void run(String... args) throws Exception {
        if (!userService.isUserExist(email)) {
            Role role = roleService.getRoleByName("ADMIN");

            Admin admin = Admin.builder()
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .build();

            admin.getRoles().add(role);

            adminService.saveAdmin(admin);
        }
    }
}
