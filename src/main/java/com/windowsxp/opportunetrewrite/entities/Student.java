package com.windowsxp.opportunetrewrite.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "students")
public class Student extends User {
    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @OneToOne(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private StudentDetail studentDetail;

    @ManyToMany(mappedBy = "responders")
    private List<Vacancy> respondedVacancies = new ArrayList<>();

    @OneToOne(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private CV cv;

    public void applyVacancy(Vacancy vacancy) {
        respondedVacancies.add(vacancy);
        vacancy.getResponders().add(this);
    }

    public void cancelVacancy(Vacancy vacancy) {
        respondedVacancies.remove(vacancy);
        vacancy.getResponders().remove(this);
    }
}
