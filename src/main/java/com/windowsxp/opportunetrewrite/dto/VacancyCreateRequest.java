package com.windowsxp.opportunetrewrite.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VacancyCreateRequest {
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Requirements is required")
    private String requirements;

    @NotBlank(message = "Employment type is required")
    private String employmentType;

    @NotBlank(message = "Work schedule is required")
    private String workScheduleType;

    @NotBlank(message = "Experience type is required")
    private String experienceType;

    @PositiveOrZero(message = "Salary must be positive or zero")
    private Float salary;

    private String currency;
}
