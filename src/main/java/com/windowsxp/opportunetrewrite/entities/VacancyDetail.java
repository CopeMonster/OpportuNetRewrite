package com.windowsxp.opportunetrewrite.entities;

import com.windowsxp.opportunetrewrite.entities.enums.Currency;
import com.windowsxp.opportunetrewrite.entities.enums.EmploymentType;
import com.windowsxp.opportunetrewrite.entities.enums.ExperienceType;
import com.windowsxp.opportunetrewrite.entities.enums.WorkScheduleType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "vacancy_details")
public class VacancyDetail {
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Vacancy vacancy;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "requirements")
    private String requirements;

    @Column(name = "employment_type", nullable = false)
    private EmploymentType employmentType;

    @Column(name = "work_schedule_type", nullable = false)
    private WorkScheduleType workScheduleType;

    @Column(name = "experience_type", nullable = false)
    private ExperienceType experienceType;

    @Column(name = "salary")
    private Float salary;

    @Column(name = "currency")
    private Currency currency;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();
}
