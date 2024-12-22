package com.mb.bookstore.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;


@Getter
@Builder
@EntityListeners(AuditingEntityListener.class)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
public class BookEntity {

    @Id
    @GeneratedValue
    Integer id;

    String title;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "author_id", nullable = false)
    AuthorEntity author;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "publisher_id", nullable = false)
    PublisherEntity publisher;

    String isbn;

    @CreatedDate
    @Column(name="created_at", nullable = false, updatable = false)
    Date createdAt;

    @LastModifiedDate
    @Column(name="updated_at", nullable = false, updatable = false)
    Date updatedAt;
}
