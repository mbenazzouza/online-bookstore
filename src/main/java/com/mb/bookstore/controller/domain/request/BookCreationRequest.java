package com.mb.bookstore.controller.domain.request;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record BookCreationRequest(@NonNull String title, @NonNull AuthorCreationRequest author, @NonNull PublisherCreationRequest publisher, String isbn) {
}
