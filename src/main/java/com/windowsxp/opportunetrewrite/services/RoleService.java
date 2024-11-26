package com.windowsxp.opportunetrewrite.services;

import com.windowsxp.opportunetrewrite.entities.Role;
import com.windowsxp.opportunetrewrite.exceptions.custom.RoleNotFoundException;
import com.windowsxp.opportunetrewrite.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("Role with id " + id + " is not found"));
    }

    public Role getRoleByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RoleNotFoundException("Role with name " + name + " is not found"));
    }

    public void addRole(String name) {
        if (roleRepository.findByName(name).isEmpty()) {
            Role role = new Role();
            role.setName(name);

            roleRepository.save(role);
        }
    }
}
