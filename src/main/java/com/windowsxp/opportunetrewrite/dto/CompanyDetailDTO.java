package com.windowsxp.opportunetrewrite.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDetailDTO {
    private String description;
    private String contactEmail;
}
