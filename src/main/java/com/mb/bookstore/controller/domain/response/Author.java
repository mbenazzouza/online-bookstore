package com.mb.bookstore.controller.domain.response;

import lombok.Builder;

@Builder
public record Author(Integer id, String firstName, String lastName) {}
