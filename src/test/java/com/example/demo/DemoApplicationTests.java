package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PagesController.class)
public class DemoApplicationTests {

    @Autowired
    MockMvc mvc;


    @Test
    public void contextLoads() throws Exception {

        RequestBuilder rq = MockMvcRequestBuilders.get("/hello");
        mvc.perform(rq)
           .andExpect(status().isOk())
           .andExpect(content().string("Hello from CNE training class!!!"));
    }

    @Test
    public  void renderPiTest() throws Exception {

        RequestBuilder rq = MockMvcRequestBuilders.get("/math/pi");
        mvc.perform(rq)
                .andExpect(status().isOk())
                .andExpect(content().string("3.141592653589793"));
    }

}
