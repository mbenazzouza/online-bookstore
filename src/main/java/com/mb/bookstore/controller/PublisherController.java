package com.mb.bookstore.controller;

import com.mb.bookstore.controller.domain.request.PublisherCreationRequest;
import com.mb.bookstore.controller.domain.response.Publisher;
import com.mb.bookstore.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    @GetMapping
    public ResponseEntity<List<Publisher>> getPublishers(){
        return ResponseEntity.ok(publisherService.getPublishers());
    }

    @PostMapping
    public ResponseEntity<?> createPublisher(@RequestBody PublisherCreationRequest createPublisherRequest) {
        publisherService.createPublisher(createPublisherRequest);
        return ResponseEntity.noContent().build();
    }
}
