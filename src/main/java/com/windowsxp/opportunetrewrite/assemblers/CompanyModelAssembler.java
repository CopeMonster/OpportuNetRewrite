package com.windowsxp.opportunetrewrite.assemblers;

import com.windowsxp.opportunetrewrite.controllers.CompanyController;
import com.windowsxp.opportunetrewrite.controllers.UserController;
import com.windowsxp.opportunetrewrite.entities.Company;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CompanyModelAssembler implements RepresentationModelAssembler<Company, EntityModel<Company>> {
    @Override
    public EntityModel<Company> toModel(Company company) {
        return EntityModel.of(company,
                linkTo(methodOn(CompanyController.class).getCompanyById(company.getId())).withSelfRel(),
                linkTo(methodOn(CompanyController.class).getCompanies()).withRel("companies"));

    }
}
