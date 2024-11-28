package com.windowsxp.opportunetrewrite.dto.responses;

import com.windowsxp.opportunetrewrite.entities.Vacancy;
import com.windowsxp.opportunetrewrite.entities.enums.Currency;
import com.windowsxp.opportunetrewrite.entities.enums.EmploymentType;
import com.windowsxp.opportunetrewrite.entities.enums.ExperienceType;
import com.windowsxp.opportunetrewrite.entities.enums.WorkScheduleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VacancyDTO {
    private Long id;
    private CompanyDTO company;
    private String title;
    private String description;
    private String requirements;
    private EmploymentType employmentType;
    private WorkScheduleType workScheduleType;
    private ExperienceType experienceType;
    private Float salary;
    private Currency currency;
    private List<StudentDTO> responders;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public static VacancyDTO from(Vacancy vacancy) {
        return VacancyDTO.builder()
                .id(vacancy.getId())
                .company(CompanyDTO.from(vacancy.getCompany()))
                .title(vacancy.getVacancyDetail().getTitle())
                .description(vacancy.getVacancyDetail().getDescription())
                .requirements(vacancy.getVacancyDetail().getRequirements())
                .employmentType(vacancy.getVacancyDetail().getEmploymentType())
                .workScheduleType(vacancy.getVacancyDetail().getWorkScheduleType())
                .experienceType(vacancy.getVacancyDetail().getExperienceType())
                .salary(vacancy.getVacancyDetail().getSalary())
                .currency(vacancy.getVacancyDetail().getCurrency())
                .responders(vacancy.getResponders().stream().map(StudentDTO::from).toList())
                .createAt(vacancy.getCreateAt())
                .updateAt(vacancy.getUpdateAt())
                .build();
    }
}
