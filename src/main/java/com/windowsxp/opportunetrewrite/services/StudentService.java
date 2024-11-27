package com.windowsxp.opportunetrewrite.services;

import com.windowsxp.opportunetrewrite.entities.Student;
import com.windowsxp.opportunetrewrite.entities.StudentDetail;
import com.windowsxp.opportunetrewrite.exceptions.custom.UserNotFoundException;
import com.windowsxp.opportunetrewrite.repositories.StudentDetailsRepository;
import com.windowsxp.opportunetrewrite.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentDetailsRepository studentDetailsRepository;

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " is not found"));
    }

    public Student getStudentByEmail(String email) {
        return studentRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email " + email + " is not found"));
    }

    public boolean isStudentExistById(Long id) {
        return studentRepository.existsById(id);
    }

    public boolean isStudentExistByEmail(String email) {
        return studentRepository.existsByEmail(email);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    public void deleteStudent(Long id) {
        if (!isStudentExistById(id)) {
            throw new UserNotFoundException("User with id " + id + " is not found");
        }

        studentRepository.deleteById(id);
    }

    @Transactional
    public StudentDetail updateStudentDetail(Long id, Map<String, Object> updates) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " is not found"));
        StudentDetail studentDetail = student.getStudentDetail();

        if (studentDetail == null) {
            throw new IllegalStateException("Student with id " + id + " does not have associated details.");
        }

        updates.forEach((key, value) -> {
            switch (key) {
                case "aboutMe" -> {
                    studentDetail.setAboutMe((String) value);
                }
                case "skills" -> {
                    if (value instanceof List) {
                        studentDetail.setSkills((List<String>) value);
                    } else {
                        throw new IllegalArgumentException("Invalid data type for skills");
                    }
                }
                case "profilePic" -> {
                    studentDetail.setProfilePic((String) value);
                }
                default -> {
                    throw new IllegalArgumentException("Unknown field: " + key);
                }
            }
        });

        studentDetailsRepository.save(studentDetail);

        return studentDetail;
    }
}
