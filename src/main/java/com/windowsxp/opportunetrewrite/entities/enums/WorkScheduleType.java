package com.windowsxp.opportunetrewrite.entities.enums;

public enum WorkScheduleType {
    FULL_DAY("Full day"),
    SHIFT_SCHEDULE("Shift schedule"),
    FLEXIBLE_SCHEDULE("Flexible schedule"),
    REMOTE_WORKING("Remote working"),
    ROTATION_BASED_WORK("Rotation based work");

    private final String name;

    WorkScheduleType(String name) {
        this.name = name;
    }
}