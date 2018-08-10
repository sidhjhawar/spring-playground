package com.example.demo.controller;

import com.example.demo.crud.LessonRepository;
import com.example.demo.model.Lesson;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.junit.Before;
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
public class LessonsControllerTest {

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    MockMvc mvc;

    Lesson lesson, lesson1;

    @Before
    public void setUp()  {
        lesson =  new Lesson();
        lesson.setDeliveredOn(new Date());
        lesson.setTitle("Learning Spring");
        lesson1 =  new Lesson();
        lesson1.setDeliveredOn(new Date());
        lesson1.setTitle("Learning Java");
        lessonRepository.save(lesson);
        lessonRepository.save(lesson1);
    }

    @Test
    @Transactional
    public void getLessonTest() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/lessons")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].title", Is.is("Learning Spring")));
    }

    @Test
    @Transactional
    public void postLessonTest() throws Exception {

        Lesson lesson3 = new Lesson();
        lesson3.setTitle("Learning CloudFoundry");
        lesson3.setDeliveredOn(new Date());
        lessonRepository.save(lesson3);
        ObjectMapper mapper =  new ObjectMapper();
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/lessons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(lesson3));
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Is.is("Learning CloudFoundry")));
    }


}