package com.windowsxp.opportunetrewrite.services;

import com.windowsxp.opportunetrewrite.entities.CV;
import com.windowsxp.opportunetrewrite.entities.Student;
import com.windowsxp.opportunetrewrite.entities.StudentDetail;
import com.windowsxp.opportunetrewrite.entities.Vacancy;
import com.windowsxp.opportunetrewrite.exceptions.custom.UserNotFoundException;
import com.windowsxp.opportunetrewrite.repositories.StudentDetailsRepository;
import com.windowsxp.opportunetrewrite.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
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

    public List<Vacancy> getRespondedVacancies(Long id, String email) {
        Student student = getStudentById(id);

        if (!student.getEmail().equals(email)) {
            throw new AccessDeniedException("You do not have permission to do this.");
        }

        return student.getRespondedVacancies();
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
    public void deleteStudent(Long id, String email) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " is not found"));

        if (!student.getEmail().equals(email)) {
            throw new AccessDeniedException("You do not have permission to do this.");
        }

        studentRepository.deleteById(id);
    }

    public StudentDetail getStudentDetail(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " is not found"));

        return student.getStudentDetail();
    }

    @Transactional
    public StudentDetail updateStudentDetail(Long id, Map<String, Object> updates, String email) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " is not found"));

        if (!student.getEmail().equals(email)) {
            throw new AccessDeniedException("You do not have permission to do this.");
        }

        StudentDetail studentDetail = student.getStudentDetail();

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

        return studentDetailsRepository.save(studentDetail);
    }

    public CV getCv(Long id, String email) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " is not found"));

        if (!student.getEmail().equals(email)) {
            throw new AccessDeniedException("You do not have permission to do this.");
        }

        return student.getCv();
    }


}
