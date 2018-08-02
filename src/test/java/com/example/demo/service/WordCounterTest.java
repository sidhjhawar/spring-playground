package com.example.demo.service;

import org.hamcrest.core.Is;
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

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class WordCounterTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    WordCounter wordCounter;

    @Test
    public void count() throws Exception {

        String input = "how now, brown cow";
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/words/count")
                .contentType(MediaType.APPLICATION_JSON)
                .content(input);

        mvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.how", Is.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.now", Is.is(1)));
    }
}