package com.example.fcm.repository;

import com.example.fcm.model.Topic;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
public class TopicRepositoryTest {

    public static final String TOPIC_NAME = "test";

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private TopicRepository topicRepository;

    @Before
    public void setUp() {
        entityManager.persist(getTopic());
        entityManager.flush();
    }

    private Topic getTopic() {
        return Topic.builder()
                .name(TOPIC_NAME)
                .build();
    }

    @Test
    public void whenFindByName_thenReturnTopic() {
//        When
        Optional<Topic> byName = topicRepository.findByName(TOPIC_NAME);
//        Then
        assertThat(byName.isPresent()).isTrue();
        assertThat(byName.get().getName()).isEqualTo(TOPIC_NAME);
    }

    @Test
    public void whenDeleteByName_thenDoNotFound() {
//        When
        topicRepository.deleteByName(TOPIC_NAME);
//        Then
        Optional<Topic> byName = topicRepository.findByName(TOPIC_NAME);
        assertThat(byName.isPresent()).isFalse();
    }

}