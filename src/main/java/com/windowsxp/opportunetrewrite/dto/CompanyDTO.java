package com.windowsxp.opportunetrewrite.dto;

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
}