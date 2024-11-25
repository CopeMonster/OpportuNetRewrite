package com.windowsxp.opportunetrewrite.services;

import com.windowsxp.opportunetrewrite.entities.CV;
import com.windowsxp.opportunetrewrite.entities.Student;
import com.windowsxp.opportunetrewrite.exceptions.custom.CVNotFoundException;
import com.windowsxp.opportunetrewrite.repositories.CVRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CVService {
    private final CVRepository cvRepository;
    private final StudentService studentService;

    public CV getCVById(Long id) {
        return cvRepository.findById(id)
                .orElseThrow(() -> new CVNotFoundException("CV with id " + id + " is not found"));
    }

    public List<CV> getAllCVs() {
        return cvRepository.findAll();
    }

    public CV getCVOfStudent(Long studentId) {
        Student student = studentService.getStudentById(studentId);
        return student.getCv();
    }
}
