package com.mb.bookstore.service;

import com.mb.bookstore.controller.domain.request.AuthorCreationRequest;
import com.mb.bookstore.controller.domain.request.BookCreationRequest;
import com.mb.bookstore.controller.domain.request.PublisherCreationRequest;
import com.mb.bookstore.controller.domain.response.Author;
import com.mb.bookstore.controller.domain.response.Book;
import com.mb.bookstore.controller.domain.response.Publisher;
import com.mb.bookstore.entity.AuthorEntity;
import com.mb.bookstore.entity.BookEntity;
import com.mb.bookstore.entity.PublisherEntity;
import com.mb.bookstore.exception.BookAlreadyExistsException;
import com.mb.bookstore.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    private final AuthorService authorService;

    private final PublisherService publisherService;

    public List<Book> getBooks() {
        var bookEntities = bookRepository.findAll();
        return bookEntities.stream().map(this::buildBookDto).toList();
    }

    private Book buildBookDto(BookEntity bookEntity) {
        return Book.builder().id(bookEntity.getId())
                .title(bookEntity.getTitle())
                .author(Author
                        .builder()
                        .firstName(bookEntity.getAuthor().getFname())
                        .lastName(bookEntity.getAuthor().getLname())
                        .build())
                .publisher(Publisher
                        .builder()
                        .name(bookEntity.getPublisher().getName())
                        .build())
                .build();
    }

    @Transactional
    public void createBook(BookCreationRequest bookCreationRequest) {
        validateBookCreation(bookCreationRequest);
        var author = authorService.getAuthor(bookCreationRequest.author().firstName(),bookCreationRequest.author().lastName());
        var publisher = publisherService.getPublisher(bookCreationRequest.publisher().name());
        if (author == null) {
            author = authorService.createAuthor(AuthorCreationRequest
                    .builder()
                    .firstName(bookCreationRequest.author().firstName())
                    .lastName(bookCreationRequest.author().lastName())
                    .build());
        }
        if (publisher == null) {
            publisher = publisherService.createPublisher(PublisherCreationRequest
                    .builder()
                    .name(bookCreationRequest.publisher().name())
                    .build());
        }
        var bookEntity = BookEntity.builder()
                .title(bookCreationRequest.title())
                .author(AuthorEntity
                        .builder()
                        .id(author.id())
                        .fname(author.firstName())
                        .lname(author.lastName())
                        .build())
                .publisher(PublisherEntity
                        .builder()
                        .id(publisher.id())
                        .name(publisher.name())
                        .build())
                .isbn(bookCreationRequest.isbn())
                .build();
        bookRepository.save(bookEntity);
    }

    private void validateBookCreation(BookCreationRequest bookCreationRequest) {
        var bookEntity = bookRepository.findByTitleAndAuthorName(bookCreationRequest.title(),bookCreationRequest.author().firstName(), bookCreationRequest.author().lastName());
        if (bookEntity != null) {
            throw new BookAlreadyExistsException(String.format("Book with title: %s by author: %s %s", bookCreationRequest.title(),bookCreationRequest.author().firstName(), bookCreationRequest.author().lastName()));
        }
    }
}
