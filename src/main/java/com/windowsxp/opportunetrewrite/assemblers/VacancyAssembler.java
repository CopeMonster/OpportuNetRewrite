package com.windowsxp.opportunetrewrite.assemblers;

import com.windowsxp.opportunetrewrite.dto.responses.VacancyDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class VacancyAssembler implements RepresentationModelAssembler<VacancyDTO, EntityModel<VacancyDTO>> {
    @Override
    public EntityModel<VacancyDTO> toModel(VacancyDTO vacancy) {
        return EntityModel.of(vacancy);
    }
}
