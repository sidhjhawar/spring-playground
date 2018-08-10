package com.example.demo.controller;

import com.example.demo.config.WordConfig;
import com.example.demo.service.WordCounter;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(secure = false)
public class WordControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    WordCounter wordCounter;

    @Test
    public void count() throws Exception {
        Map<String, Integer> returnMap =  new HashMap<>();
        returnMap.put("how", 1);
        returnMap.put("now", 1);
        returnMap.put("cow", 1);
        returnMap.put("brown",1);
        String input = "how now, brown cow";
        Mockito.when(wordCounter.count(input)).thenReturn(returnMap);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/words/count")
                .contentType(MediaType.APPLICATION_JSON)
                .content(input);

        mvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.how", Is.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.now", Is.is(1)));
    }

}