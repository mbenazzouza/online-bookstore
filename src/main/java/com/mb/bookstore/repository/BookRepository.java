package com.mb.bookstore.repository;

import com.mb.bookstore.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    @Query("select b from BookEntity b where b.title=:title and b.author in (select a from AuthorEntity a where a.fname=:authorFName and a.lname=:authorLName)")
    BookEntity findByTitleAndAuthorName(@Param("title") String title, @Param("authorFName") String authorFName, @Param("authorLName") String authorLName);
}
