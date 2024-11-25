package com.windowsxp.opportunetrewrite.exceptions.custom;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class VacancyNotFoundException extends RuntimeException {
    private final String message;
}
