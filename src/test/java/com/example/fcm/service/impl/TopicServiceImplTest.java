package com.example.fcm.service.impl;

import com.example.fcm.model.Topic;
import com.example.fcm.repository.TopicRepository;
import com.example.fcm.service.TopicService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DataJpaTest
@RunWith(SpringRunner.class)
public class TopicServiceImplTest {

    public static final String TOPIC_NAME = "topic_name";
    public static final int ID = 1;

    @TestConfiguration
    public static class TopicServiceImplTestConfigurations {

        @Bean
        public TopicService topicService() {
            return new TopicServiceImpl();
        }
    }

    @Autowired
    private TopicService topicService;

    @MockBean
    private TopicRepository topicRepository;

    @Before
    public void setUp() {
        Topic topic = getTopic();
        when(topicRepository.save(any(Topic.class))).thenReturn(topic);
        when(topicRepository.findByName(TOPIC_NAME)).thenReturn(Optional.of(topic));
    }

    private static Topic getTopic() {
        return Topic.builder()
            .name(TOPIC_NAME)
            .id(ID)
            .build();
    }

    @Test
    public void create() {
//        When
        final Topic topicCreated = topicService.create(TOPIC_NAME);
//        Then
        assertThat(topicCreated.getId()).isEqualTo(ID);
        verify(topicRepository, atMost(1)).save(any(Topic.class));
    }

    @Test
    public void delete() {
        topicService.delete(TOPIC_NAME);
        verify(topicRepository, atMostOnce()).deleteByName(TOPIC_NAME);
    }

    @Test
    public void findByName() {
        final Optional<Topic> byName = topicService.findByName(TOPIC_NAME);
        assertThat(byName.isPresent()).isTrue();
        assertThat(byName.get().getName()).isEqualTo(TOPIC_NAME);
    }
}
