package com.windowsxp.opportunetrewrite.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity
@Table(name = "companies")
public class Company extends User implements Serializable {
    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "BIN", nullable = false, unique = true)
    private String BIN;

    @OneToOne(mappedBy = "company", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Builder.Default
    private CompanyDetail companyDetail = new CompanyDetail();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vacancy> vacancies = new ArrayList<>();
}
