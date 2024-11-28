package com.windowsxp.opportunetrewrite.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VacancyRespondResponseDTO {
    private String studentEmail;
    private String vacancyTitle;
    private String vacancyCompany;
    private String vacancyDescription;
    private String message;
}
