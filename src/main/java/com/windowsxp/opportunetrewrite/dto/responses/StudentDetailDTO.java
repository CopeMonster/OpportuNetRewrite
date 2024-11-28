package com.windowsxp.opportunetrewrite.dto.responses;

import com.windowsxp.opportunetrewrite.entities.StudentDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDetailDTO {
    private String aboutMe;
    private List<String> skills;
    private String profilePic;

    public static StudentDetailDTO from(StudentDetail studentDetail) {
        return StudentDetailDTO.builder()
                .aboutMe(studentDetail.getAboutMe())
                .skills(studentDetail.getSkills())
                .profilePic(studentDetail.getProfilePic())
                .build();
    }
}
