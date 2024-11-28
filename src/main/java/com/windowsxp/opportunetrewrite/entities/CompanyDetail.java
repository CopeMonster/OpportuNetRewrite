package com.windowsxp.opportunetrewrite.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "company_details")
public class CompanyDetail {
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Company company;

    @Column(name = "description")
    private String description = "";

    @Column(name = "contact_email", nullable = false)
    private String contactEmail = "";
}
