package com.windowsxp.opportunetrewrite.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "student_details")
public class StudentDetail {
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Student student;

    @Column(name = "about_me")
    private String aboutMe;

    @ElementCollection(targetClass = String.class)
    @Column(name = "skills")
    private List<String> skills;

    @Column(name = "profile_pic")
    private String profilePic;
}
