package com.windowsxp.opportunetrewrite.entities.enums;

import lombok.Getter;

@Getter
public enum ExperienceType {
    NO_EXPERIENCE("No experience"),
    BETWEEN_1_TO_3_YEARS("1-3 years"),
    BETWEEN_3_TO_6_YEARS("3-6 years");

    private final String name;

    ExperienceType(String name) {
        this.name = name;
    }
}