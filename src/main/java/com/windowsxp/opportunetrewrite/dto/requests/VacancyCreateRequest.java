package com.windowsxp.opportunetrewrite.dto.requests;

import com.windowsxp.opportunetrewrite.entities.enums.Currency;
import com.windowsxp.opportunetrewrite.entities.enums.EmploymentType;
import com.windowsxp.opportunetrewrite.entities.enums.ExperienceType;
import com.windowsxp.opportunetrewrite.entities.enums.WorkScheduleType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType;

    @Enumerated(EnumType.STRING)
    private WorkScheduleType workScheduleType;

    @Enumerated(EnumType.STRING)
    private ExperienceType experienceType;

    @PositiveOrZero(message = "Salary must be positive or zero")
    private Float salary;

    private Currency currency;
}
