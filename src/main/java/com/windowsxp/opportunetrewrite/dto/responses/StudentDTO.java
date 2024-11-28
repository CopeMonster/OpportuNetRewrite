package com.windowsxp.opportunetrewrite.dto.responses;

import com.windowsxp.opportunetrewrite.entities.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDTO {
    private Long id;
    private String email;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public static StudentDTO from(Student student) {
        return StudentDTO.builder()
                .id(student.getId())
                .email(student.getEmail())
                .createAt(student.getCreateAt())
                .updateAt(student.getUpdateAt())
                .build();
    }
}
