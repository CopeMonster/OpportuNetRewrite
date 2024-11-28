package com.windowsxp.opportunetrewrite.assemblers;

import com.windowsxp.opportunetrewrite.controllers.StudentController;
import com.windowsxp.opportunetrewrite.entities.Student;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component

public class StudentAssembler implements RepresentationModelAssembler<Student, EntityModel<Student>> {
    @Override
    public EntityModel<Student> toModel(Student student) {
        return EntityModel.of(student,
                linkTo(methodOn(StudentController.class).getStudentById(student.getId())).withSelfRel(),
                linkTo(methodOn(StudentController.class).getAllStudents()).withRel("users"));
    }
}
