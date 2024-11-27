package com.windowsxp.opportunetrewrite.controllers;

import com.windowsxp.opportunetrewrite.assemblers.VacancyAssembler;
import com.windowsxp.opportunetrewrite.entities.Vacancy;
import com.windowsxp.opportunetrewrite.services.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vacancies")
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;
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
}
