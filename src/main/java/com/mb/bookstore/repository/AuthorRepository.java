package com.mb.bookstore.repository;

import com.mb.bookstore.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

    Optional<AuthorEntity> findByFnameAndLname(String firstName, String lastName);
}
