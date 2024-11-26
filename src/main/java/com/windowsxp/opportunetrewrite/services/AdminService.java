package com.windowsxp.opportunetrewrite.services;

import com.windowsxp.opportunetrewrite.entities.Admin;
import com.windowsxp.opportunetrewrite.repositories.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;

    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }
}
