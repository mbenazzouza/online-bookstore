package com.mb.bookstore.controller;

import com.mb.bookstore.controller.domain.request.BookCreationRequest;
import com.mb.bookstore.controller.domain.response.Book;
import com.mb.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
@RestController
public class BookController {
    private final BookService bookService;

    @GetMapping
    @ResponseBody
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody BookCreationRequest bookCreationRequest) {
        bookService.createBook(bookCreationRequest);
        return ResponseEntity.noContent().build();
    }
}
