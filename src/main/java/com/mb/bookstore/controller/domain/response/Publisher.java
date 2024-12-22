package com.mb.bookstore.controller.domain.response;

import lombok.Builder;

@Builder
public record Publisher(Integer id, String name) {
}
