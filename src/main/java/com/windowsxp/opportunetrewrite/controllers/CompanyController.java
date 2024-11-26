package com.windowsxp.opportunetrewrite.controllers;

import com.windowsxp.opportunetrewrite.assemblers.CompanyModelAssembler;
import com.windowsxp.opportunetrewrite.entities.Company;
import com.windowsxp.opportunetrewrite.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;
    private final CompanyModelAssembler companyModelAssembler;

    @GetMapping("/{companyId}")
    public EntityModel<Company> getCompanyById(@PathVariable Long companyId) {
        Company company = companyService.getCompanyById(companyId);

        return companyModelAssembler.toModel(company);
    }

    @GetMapping
    public CollectionModel<EntityModel<Company>> getCompanies() {
        List<EntityModel<Company>> companies = companyService.getAllCompanies()
                .stream()
                .map(companyModelAssembler::toModel)
                .toList();

        return CollectionModel.of(companies,
                linkTo(methodOn(CompanyController.class).getCompanies()).withSelfRel());
    }

    @DeleteMapping("/{companyId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteCompany(@PathVariable Long companyId) {
        companyService.deleteCompany(companyId);

        return ResponseEntity.ok("Company with id " + companyId + " was deleted");
    }
}
