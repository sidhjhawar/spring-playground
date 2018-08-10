package com.example.demo.controller;


import com.example.demo.crud.LessonRepository;
import com.example.demo.model.Lesson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@SpringBootTest
@AutoConfigureMockMvc(secure = false)
@RunWith(SpringRunner.class)
public class DeleteEndpointTest {

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    MockMvc mvc;

    @Test
    @Transactional
    public void deleteLesson() throws Exception {
        Lesson lesson, lesson1;
        lesson =  new Lesson();
        lesson.setDeliveredOn(new Date());
        lesson.setTitle("Learning Spring");
        lesson1 =  new Lesson();
        lesson1.setDeliveredOn(new Date());
        lesson1.setTitle("Learning Java");
        lessonRepository.save(lesson);
        lessonRepository.save(lesson1);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/lessons/1")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
