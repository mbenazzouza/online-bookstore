package com.mb.bookstore.service;

import com.mb.bookstore.controller.domain.request.AuthorCreationRequest;
import com.mb.bookstore.controller.domain.response.Author;
import com.mb.bookstore.entity.AuthorEntity;
import com.mb.bookstore.exception.AuthorNotFoundException;
import com.mb.bookstore.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<Author> getAuthors() {
        List<AuthorEntity> authorEntities = authorRepository.findAll();

        return authorEntities.stream().map(this::buildAuthor).toList();
    }

    private Author buildAuthor(AuthorEntity authorEntity) {
        return Author.builder()
                .id(authorEntity.getId())
                .firstName(authorEntity.getFname())
                .lastName(authorEntity.getLname())
                .build();
    }


    public Author getAuthor(String firstName, String lastName) {
        var authorEntity = authorRepository.findByFnameAndLname(firstName, lastName);
        if (authorEntity.isEmpty()) {
            throw new AuthorNotFoundException(String.format("User  %s %s not found",firstName, lastName));
        }
        return buildAuthor(authorEntity.get());
    }

    @Transactional
    public Author createAuthor(AuthorCreationRequest creationRequest) {
        var authorEntity = AuthorEntity.builder()
                .fname(creationRequest.firstName())
                .lname(creationRequest.lastName())
                .build();

        return buildAuthor(authorRepository.save(authorEntity));
    }
}

