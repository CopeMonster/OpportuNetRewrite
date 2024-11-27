package com.windowsxp.opportunetrewrite.controllers;

import com.windowsxp.opportunetrewrite.assemblers.StudentAssembler;
import com.windowsxp.opportunetrewrite.entities.CV;
import com.windowsxp.opportunetrewrite.entities.Student;
import com.windowsxp.opportunetrewrite.entities.StudentDetail;
import com.windowsxp.opportunetrewrite.entities.Vacancy;
import com.windowsxp.opportunetrewrite.services.StudentService;
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

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final StudentAssembler studentAssembler;

    @GetMapping("/{studentId}")
    public EntityModel<Student> getStudentById(@PathVariable Long studentId) {
        Student student = studentService.getStudentById(studentId);

        return studentAssembler.toModel(student);
    }

    @GetMapping
    public CollectionModel<EntityModel<Student>> getAllStudents() {
        List<EntityModel<Student>> students = studentService.getAllStudents()
                .stream()
                .map(studentAssembler::toModel)
                .toList();

        return CollectionModel.of(students);
    }

    @GetMapping("/{studentId}/details")
    public ResponseEntity<StudentDetail> getStudentDetails(@PathVariable Long studentId) {
        Student student = studentService.getStudentById(studentId);

        StudentDetail studentDetail = student.getStudentDetail();

        return ResponseEntity.ok(studentDetail);
    }

    @PatchMapping("/{studentId}/details")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity<StudentDetail> updateStudentDetails(
            @PathVariable Long studentId,
            @RequestBody Map<String, Object> updates,
            @AuthenticationPrincipal UserDetails userDetails)
    {
        StudentDetail studentDetail = studentService.updateStudentDetail(studentId, updates, userDetails.getUsername());

        return ResponseEntity.ok(studentDetail);
    }

    @GetMapping("/{studentId}/cv")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity<CV> getStudentCV(
            @PathVariable Long studentId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        CV cv = studentService.getCv(studentId, userDetails.getUsername());

        return ResponseEntity.ok(cv);
    }

    @PostMapping("/{studentId}/cv")
    public ResponseEntity<CV> uploadStudentCV(
            @PathVariable Long studentId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        // TODO: implement upload logic

        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{studentId}")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity<String> deleteStudent(
            @PathVariable Long studentId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        studentService.deleteStudent(studentId, userDetails.getUsername());

        return ResponseEntity.ok("Student with id " + studentId + " was deleted");
    }

    @GetMapping("/{studentId}/respondedVacancies")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity<List<Vacancy>> getRespondedVacancies(
            @PathVariable Long studentId,
            @AuthenticationPrincipal UserDetails userDetails)
    {
        List<Vacancy> vacancies = studentService.getRespondedVacancies(studentId, userDetails.getUsername());

        return ResponseEntity.ok(vacancies);
    }
}
