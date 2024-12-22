package com.mb.bookstore.service;

import com.mb.bookstore.controller.domain.request.PublisherCreationRequest;
import com.mb.bookstore.controller.domain.response.Publisher;
import com.mb.bookstore.entity.PublisherEntity;
import com.mb.bookstore.exception.PublisherNotFoundException;
import com.mb.bookstore.repository.PublisherRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public List<Publisher> getPublishers() {
        var publisherEntities = publisherRepository.findAll();
        return publisherEntities
                .stream()
                .map(this::buildPublisherDTO)
                .toList();
    }

    public Publisher getPublisher(String name) {
        var publisherEntity = publisherRepository.findByName(name);
        if (publisherEntity.isEmpty()) {
            throw new PublisherNotFoundException(String.format("Publisher %s not found", name));
        }
        return buildPublisherDTO(publisherEntity.get());
    }

    private Publisher buildPublisherDTO(PublisherEntity publisherEntity) {
        return Publisher
                .builder()
                .id(publisherEntity.getId())
                .name(publisherEntity.getName())
                .build();
    }

    @Transactional
    public Publisher createPublisher(PublisherCreationRequest createPublisherRequest) {
        var publisherEntity = PublisherEntity.builder()
                .name(createPublisherRequest.name()).build();
        return buildPublisherDTO(publisherRepository.save(publisherEntity));
    }
}
