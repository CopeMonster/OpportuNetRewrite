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
    public ResponseEntity<StudentDetail> updateStudentDetails(@PathVariable Long studentId, @RequestBody Map<String, Object> updates) {
        StudentDetail studentDetail = studentService.updateStudentDetail(studentId, updates);

        return ResponseEntity.ok(studentDetail);
    }

    @GetMapping("/{studentId}/cv")
    public ResponseEntity<CV> getStudentCV(@PathVariable Long studentId) {
        Student student = studentService.getStudentById(studentId);

        CV cv = student.getCv();

        return ResponseEntity.ok(cv);
    }

    @PostMapping("/{studentId}/cv")
    public ResponseEntity<CV> uploadStudentCV(@PathVariable Long studentId) {
        Student student = studentService.getStudentById(studentId);

        // TODO: implement upload logic

        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);

        return ResponseEntity.ok("Student with id " + studentId + " was deleted");
    }

    @GetMapping("/{studentId}/respondedVacancies")
    public ResponseEntity<List<Vacancy>> getRespondedVacancies(@PathVariable Long studentId) {
        Student student = studentService.getStudentById(studentId);

        List<Vacancy> vacancies = student.getRespondedVacancies();

        return ResponseEntity.ok(vacancies);
    }
}
