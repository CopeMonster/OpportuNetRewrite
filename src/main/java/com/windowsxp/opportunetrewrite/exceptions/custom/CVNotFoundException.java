package com.windowsxp.opportunetrewrite.exceptions.custom;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CVNotFoundException extends RuntimeException {
    private final String message;
}
