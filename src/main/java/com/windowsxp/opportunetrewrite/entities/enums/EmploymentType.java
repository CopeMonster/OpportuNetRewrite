package com.windowsxp.opportunetrewrite.entities.enums;

import lombok.Getter;

@Getter
public enum EmploymentType {
    FULL_TIME("Full time"),
    PART_TIME("Part time"),
    PROJECT_WORK("Project work"),
    VOLUNTEERING("Volunteering"),
    WORK_PLACEMENT("Work placement");

    private final String name;

    EmploymentType(String name) {
        this.name = name;
    }
}