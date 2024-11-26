package com.windowsxp.opportunetrewrite.config.init;

import com.windowsxp.opportunetrewrite.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(1)
public class RoleInitializer implements CommandLineRunner {
    private final RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        roleService.addRole("USER");
        roleService.addRole("ADMIN");
        roleService.addRole("STUDENT");
        roleService.addRole("COMPANY");
    }
}
