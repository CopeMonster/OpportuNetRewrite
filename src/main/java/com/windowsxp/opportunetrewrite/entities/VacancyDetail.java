package com.windowsxp.opportunetrewrite.entities;

import com.windowsxp.opportunetrewrite.entities.enums.Currency;
import com.windowsxp.opportunetrewrite.entities.enums.EmploymentType;
import com.windowsxp.opportunetrewrite.entities.enums.ExperienceType;
import com.windowsxp.opportunetrewrite.entities.enums.WorkScheduleType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "vacancy_details")
public class VacancyDetail {
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Vacancy vacancy;

    @Column(name = "title", nullable = false)
    private String title = "";

    @Column(name = "description", nullable = false)
    private String description = "";

    @Column(name = "requirements")
    private String requirements = "";

    @Enumerated(EnumType.STRING)
    @Column(name = "employment_type", nullable = false)
    private EmploymentType employmentType = EmploymentType.FULL_TIME;

    @Enumerated(EnumType.STRING)
    @Column(name = "work_schedule_type", nullable = false)
    private WorkScheduleType workScheduleType = WorkScheduleType.FULL_DAY;

    @Enumerated(EnumType.STRING)
    @Column(name = "experience_type", nullable = false)
    private ExperienceType experienceType = ExperienceType.NO_EXPERIENCE;

    @Column(name = "salary")
    private Float salary = Float.valueOf(0);

    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private Currency currency = Currency.KZT;
}
