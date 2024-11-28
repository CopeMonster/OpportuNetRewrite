package com.windowsxp.opportunetrewrite.dto.responses;

import com.windowsxp.opportunetrewrite.entities.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDTO {
    private Long id;
    private String email;
    private String BIN;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public static CompanyDTO from(Company company) {
        return CompanyDTO.builder()
                .id(company.getId())
                .email(company.getEmail())
                .BIN(company.getBIN())
                .createAt(company.getCreateAt())
                .updateAt(company.getUpdateAt())
                .build();
    }
}
