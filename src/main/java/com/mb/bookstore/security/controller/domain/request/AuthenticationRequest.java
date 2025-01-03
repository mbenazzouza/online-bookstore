package com.mb.bookstore.security.controller.domain.request;

import lombok.Builder;

@Builder
public record AuthenticationRequest(String email, String password) {
}
