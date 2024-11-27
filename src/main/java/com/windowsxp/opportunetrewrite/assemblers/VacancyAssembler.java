package com.windowsxp.opportunetrewrite.assemblers;

import com.windowsxp.opportunetrewrite.entities.Vacancy;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class VacancyAssembler implements RepresentationModelAssembler<Vacancy, EntityModel<Vacancy>> {
    @Override
    public EntityModel<Vacancy> toModel(Vacancy vacancy) {
        return EntityModel.of(vacancy);
    }
}
