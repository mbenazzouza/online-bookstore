package com.mb.bookstore.controller;

import com.mb.bookstore.controller.domain.request.AuthorCreationRequest;
import com.mb.bookstore.controller.domain.response.Author;
import com.mb.bookstore.service.AuthorService;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/api/v1/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<Author>> getAuthor(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {
        if (StringUtils.isBlank(firstName) && StringUtils.isBlank(lastName)) {
            return ResponseEntity.ok(authorService.getAuthors());
        }
        return ResponseEntity.ok(Collections.singletonList(authorService.getAuthor(firstName, lastName)));
    }

    @PostMapping
    public ResponseEntity<?> createAuthor(@RequestBody AuthorCreationRequest creationRequest) {
        authorService.createAuthor(creationRequest);
        return ResponseEntity.noContent().build();
    }

}
