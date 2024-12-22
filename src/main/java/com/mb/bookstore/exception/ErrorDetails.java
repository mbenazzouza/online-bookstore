package com.mb.bookstore.exception;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
public class ErrorDetails {
    private final String message;
    private final String details;
}
