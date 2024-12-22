package com.mb.bookstore.controller.domain.response;

import lombok.Builder;

@Builder
public record Book(Integer id, String title, Author author, Publisher publisher, String isbn) {
}
