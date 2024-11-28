package com.windowsxp.opportunetrewrite.controllers;

import com.windowsxp.opportunetrewrite.assemblers.CompanyModelAssembler;
import com.windowsxp.opportunetrewrite.dto.CompanyDTO;
import com.windowsxp.opportunetrewrite.dto.CompanyDetailDTO;
import com.windowsxp.opportunetrewrite.entities.Company;
import com.windowsxp.opportunetrewrite.entities.CompanyDetail;
import com.windowsxp.opportunetrewrite.entities.Vacancy;
import com.windowsxp.opportunetrewrite.services.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;
    private final CompanyModelAssembler companyModelAssembler;

    @GetMapping("/{companyId}")
    public EntityModel<CompanyDTO> getCompanyById(@PathVariable Long companyId) {
        Company company = companyService.getCompanyById(companyId);

        CompanyDTO companyDTO = CompanyDTO.builder()
                .id(company.getId())
                .email(company.getEmail())
                .BIN(company.getBIN())
                .createAt(company.getCreateAt())
                .updateAt(company.getUpdateAt())
                .build();

        return companyModelAssembler.toModel(companyDTO);
    }

    @GetMapping
    public CollectionModel<EntityModel<CompanyDTO>> getCompanies() {
        List<CompanyDTO> companyDTOS = companyService.getAllCompanies()
                .stream()
                .map(company -> CompanyDTO.builder()
                        .id(company.getId())
                        .email(company.getEmail())
                        .BIN(company.getBIN())
                        .createAt(company.getCreateAt())
                        .updateAt(company.getUpdateAt())
                        .build())
                .toList();

        List<EntityModel<CompanyDTO>> companies = companyDTOS
                .stream()
                .map(companyModelAssembler::toModel)
                .toList();

        return CollectionModel.of(companies,
                linkTo(methodOn(CompanyController.class).getCompanies()).withSelfRel());
    }

    @DeleteMapping("/{companyId}")
    @PreAuthorize("hasRole('ROLE_COMPANY')")
    public ResponseEntity<String> deleteCompany(
            @PathVariable Long companyId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        companyService.deleteCompany(companyId, userDetails.getUsername());

        return ResponseEntity.ok("Company with id " + companyId + " was deleted");
    }

    @GetMapping("/{companyId}/details")
    public ResponseEntity<CompanyDetailDTO> getCompanyDetails(@PathVariable Long companyId) {
        CompanyDetail companyDetail = companyService.getCompanyDetail(companyId);

        return ResponseEntity.ok(CompanyDetailDTO.builder()
                .description(companyDetail.getDescription())
                .contactEmail(companyDetail.getContactEmail())
                .build());
    }

    @PatchMapping("/{companyId}/details")
    @PreAuthorize("hasRole('ROLE_COMPANY')")
    public ResponseEntity<CompanyDetailDTO> updateCompanyDetails(
            @PathVariable Long companyId,
            @Valid @RequestBody Map<String, Object> updates,
            @AuthenticationPrincipal UserDetails userDetails) {
        CompanyDetail companyDetail =
                companyService.updateCompanyDetail(companyId, updates, userDetails.getUsername());

        return ResponseEntity.ok(CompanyDetailDTO.builder()
                .description(companyDetail.getDescription())
                .contactEmail(companyDetail.getContactEmail())
                .build());
    }

    @GetMapping("/{companyId}/vacancies")
    public ResponseEntity<List<Vacancy>> getCompanyVacancies(
            @PathVariable Long companyId
    ) {
        List<Vacancy> vacancies = companyService.getVacancies(companyId);

        return ResponseEntity.ok(vacancies);
    }
}
