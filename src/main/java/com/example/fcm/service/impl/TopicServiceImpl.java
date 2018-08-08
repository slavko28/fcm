package com.example.fcm.service.impl;

import com.example.fcm.model.Topic;
import com.example.fcm.repository.TopicRepository;
import com.example.fcm.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    @Autowired
    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    @Transactional
    public Topic create(String topicName) {
        if (findByName(topicName).isPresent()){
            throw new IllegalArgumentException(String.format("Topic with name: '%s' - is already exists", topicName));
        }
        return topicRepository.save(Topic.builder()
                .name(topicName)
                .build());
    }

    @Override
    @Transactional
    public void delete(String topicName) {
        topicRepository.deleteByName(topicName);
    }

    @Override
    public Optional<Topic> findByName(String name) {
        return topicRepository.findByName(name);
    }
}
