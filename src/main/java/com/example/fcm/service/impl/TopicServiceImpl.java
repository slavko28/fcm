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

    @Autowired
    private TopicRepository topicRepository;

    @Override
    @Transactional
    public Topic create(String topicName) {
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
