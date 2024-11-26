package com.windowsxp.opportunetrewrite.exceptions.custom;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RoleNotFoundException extends RuntimeException {
    private final String message;
}
