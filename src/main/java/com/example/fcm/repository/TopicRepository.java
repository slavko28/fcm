package com.example.fcm.repository;

import com.example.fcm.model.Topic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicRepository extends CrudRepository<Topic, Integer>{
    Optional<Topic> findByName(String name);

    void deleteByName(String topicName);
}
