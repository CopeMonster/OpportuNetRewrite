package com.windowsxp.opportunetrewrite.services;

import com.windowsxp.opportunetrewrite.entities.Student;
import com.windowsxp.opportunetrewrite.entities.StudentDetail;
import com.windowsxp.opportunetrewrite.exceptions.custom.UserNotFoundException;
import com.windowsxp.opportunetrewrite.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " is not found"));
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public StudentDetail updateStudentDetail(Long id, StudentDetail updatedDetail) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " is not found"));
        StudentDetail studentDetail = student.getStudentDetail();

        if (studentDetail == null) {
            throw new IllegalStateException("Student with id " + id + " does not have associated details.");
        }

        studentDetail.setAboutMe(updatedDetail.getAboutMe());
        studentDetail.setSkills(updatedDetail.getSkills());
        studentDetail.setProfilePic(updatedDetail.getProfilePic());

        studentRepository.save(student);

        return studentDetail;
    }
}