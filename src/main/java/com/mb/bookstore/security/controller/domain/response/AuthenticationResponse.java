package com.mb.bookstore.security.controller.domain.response;

import lombok.Builder;

@Builder
public record AuthenticationResponse(String token) {


}
