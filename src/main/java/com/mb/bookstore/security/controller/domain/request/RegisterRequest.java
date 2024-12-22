package com.mb.bookstore.security.controller.domain.request;

import lombok.Builder;

@Builder
public record RegisterRequest(String fName, String lName, String email, String password) {
}
