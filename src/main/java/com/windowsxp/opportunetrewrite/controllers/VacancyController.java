package com.windowsxp.opportunetrewrite.controllers;

import com.windowsxp.opportunetrewrite.assemblers.VacancyAssembler;
import com.windowsxp.opportunetrewrite.dto.VacancyCreateRequest;
import com.windowsxp.opportunetrewrite.entities.Company;
import com.windowsxp.opportunetrewrite.entities.Vacancy;
import com.windowsxp.opportunetrewrite.services.CompanyService;
import com.windowsxp.opportunetrewrite.services.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacancies")
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;
    private final CompanyService companyService;
    private final VacancyAssembler vacancyAssembler;

    @GetMapping("/{vacancyId}")
    public EntityModel<Vacancy> getVacancyById(@PathVariable Long vacancyId) {
        Vacancy vacancy = vacancyService.getVacancyById(vacancyId);

        return vacancyAssembler.toModel(vacancy);
    }

    @GetMapping
    public CollectionModel<EntityModel<Vacancy>> getAllVacancies() {
        List<EntityModel<Vacancy>> vacancies = vacancyService.getVacancies()
                .stream()
                .map(vacancyAssembler::toModel)
                .toList();

        return CollectionModel.of(vacancies);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_COMPANY')")
    public ResponseEntity<Vacancy> createVacancy(@RequestBody VacancyCreateRequest request, @AuthenticationPrincipal UserDetails userDetails) {
        Company company = companyService.getCompanyByEmail(userDetails.getUsername());

        Vacancy createdVacancy = vacancyService.createVacancy(request, company);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVacancy);
    }

    @DeleteMapping("/{vacancyId}")
    @PreAuthorize("hasRole('ROLE_COMPANY')")
    public ResponseEntity<String> deleteVacancy(@PathVariable Long vacancyId, @AuthenticationPrincipal UserDetails userDetails) {
        Company company = companyService.getCompanyByEmail(userDetails.getUsername());

        vacancyService.deleteVacancy(vacancyId, company);

        return ResponseEntity.ok("Company with id " + vacancyId + " was deleted");
    }
}
