package com.windowsxp.opportunetrewrite.dto;

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
}
