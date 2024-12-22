package com.mb.bookstore.controller.domain.request;


import lombok.Builder;
import lombok.NonNull;

@Builder
public record AuthorCreationRequest(@NonNull String firstName, @NonNull String lastName) {

}
