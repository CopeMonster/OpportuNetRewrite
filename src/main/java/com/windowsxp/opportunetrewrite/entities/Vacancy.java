package com.windowsxp.opportunetrewrite.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "vacancies")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "vacancies_students",
            joinColumns = @JoinColumn(name = "vacancy_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> responders = new ArrayList<>();

    @OneToOne(mappedBy = "vacancy", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Builder.Default
    private VacancyDetail vacancyDetail = new VacancyDetail();

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt;

    public void addResponder(Student student) {
        responders.add(student);
        student.getRespondedVacancies().add(this);
    }

    public void removeResponder(Student student) {
        responders.remove(student);
        student.getRespondedVacancies().remove(this);
    }

    @PrePersist
    protected void onCreate() {
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateAt = LocalDateTime.now();
    }

    public void setVacancyDetail(VacancyDetail vacancyDetail) {
        this.vacancyDetail = vacancyDetail;
        if (vacancyDetail != null) {
            vacancyDetail.setVacancy(this);
        }
    }
}
