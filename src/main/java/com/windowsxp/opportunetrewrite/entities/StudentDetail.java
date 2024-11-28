package com.windowsxp.opportunetrewrite.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "student_details")
public class StudentDetail implements Serializable {
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Student student;

    @Column(name = "about_me")
    private String aboutMe = "";

    @ElementCollection(targetClass = String.class)
    @Column(name = "skills")
    private List<String> skills = new ArrayList<>();

    @Column(name = "profile_pic")
    private String profilePic = "";
}
