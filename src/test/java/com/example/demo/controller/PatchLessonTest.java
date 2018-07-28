package com.example.demo.controller;


import com.example.demo.crud.LessonRepository;
import com.example.demo.model.Lesson;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Iterator;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class PatchLessonTest {

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

    @After
    public void destroy() {
        lesson = null;
        lesson1 = null;
    }

    @Test
    @Transactional
    @Rollback
    public void patchLessonTest() throws Exception {
        lesson =  new Lesson();
        lesson.setDeliveredOn(new Date());
        lesson.setTitle("Learning Spring");
        lesson1 =  new Lesson();
        lesson1.setDeliveredOn(new Date());
        lesson1.setTitle("Learning Java");
        lessonRepository.save(lesson);
        lessonRepository.save(lesson1);
        Lesson lesson3 = new Lesson();
        lesson3.setTitle("Learning Kubernetes");
        Iterable<Lesson> all = lessonRepository.findAll();
        Iterator i = all.iterator();
        Lesson l = (Lesson) i.next();
        ObjectMapper mapper =  new ObjectMapper();
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.patch(String.format("/lessons/%d", l.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(lesson3));
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Is.is("Learning Kubernetes")));
    }


}
