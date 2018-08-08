package com.example.fcm.service;

import com.example.fcm.model.Topic;

import java.util.Optional;

public interface TopicService {

    Topic create(String topicName);
    void delete(String name);
    Optional<Topic> findByName(String name);
}
