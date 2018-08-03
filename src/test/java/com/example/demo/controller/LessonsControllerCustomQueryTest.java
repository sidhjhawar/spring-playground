package com.example.demo.controller;

import com.example.demo.crud.LessonRepository;
import com.example.demo.model.Lesson;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Ignore;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class LessonsControllerCustomQueryTest {

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    MockMvc mvc;

    Lesson lesson, lesson1;

    @Before
    public void setUp() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        lesson =  new Lesson();
        lesson.setDeliveredOn(sdf.parse("2017-04-12"));
        lesson.setTitle("Learning Spring");
        lesson1 =  new Lesson();
        lesson1.setDeliveredOn(sdf.parse("2013-05-02"));
        lesson1.setTitle("Learning Java");
        lessonRepository.save(lesson);
        lessonRepository.save(lesson1);
    }


    @Test
    public void findLessonByTitle() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(String.format("/lessons/find/%s", lesson.getTitle()))
            .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Is.is("Learning Spring")));
    }

    @Test
    public void findLessonBetweenDates() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/lessons/between")
                .param("date1", "2014-01-01")
                .param("date2", "2017-12-31")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].title", Is.is("Learning Spring")));
    }

}