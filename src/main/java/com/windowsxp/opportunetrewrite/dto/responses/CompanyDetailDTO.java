package com.windowsxp.opportunetrewrite.dto.responses;

import com.windowsxp.opportunetrewrite.entities.CompanyDetail;
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

    public static CompanyDetailDTO from(CompanyDetail companyDetail) {
        return CompanyDetailDTO.builder()
                .description(companyDetail.getDescription())
                .contactEmail(companyDetail.getContactEmail())
                .build();
    }
}
