package com.mb.bookstore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.Set;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "authors")
public class AuthorEntity {

    @Id
    @GeneratedValue
    Integer id;

    String fname;

    String lname;

    @OneToMany(mappedBy = "author")
    private Set<BookEntity> books;


    @CreatedDate
    @Column(name="created_at", nullable = false, updatable = false)
    Date createdAt;

    @LastModifiedDate
    @Column(name="updated_at", nullable = false, updatable = false)
    Date updatedAt;

}
