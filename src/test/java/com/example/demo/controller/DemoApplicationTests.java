package com.example.demo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
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

    @Test
    public void addTest() throws Exception {
        RequestBuilder rq = MockMvcRequestBuilders.get("/math/calculate?operation=add&x=4&y=6");
        mvc.perform(rq)
                .andExpect(status().isOk())
                .andExpect(content().string("10"));

    }

    @Test
    public void subtractTest() throws Exception {
        RequestBuilder rq = MockMvcRequestBuilders.get("/math/calculate?operation=subtract&x=6&y=2");
        mvc.perform(rq)
                .andExpect(status().isOk())
                .andExpect(content().string("4"));

    }

    @Test
    public void multiplyTest() throws Exception {
        RequestBuilder rq = MockMvcRequestBuilders.get("/math/calculate?operation=multiply&x=6&y=2");
        mvc.perform(rq)
                .andExpect(status().isOk())
                .andExpect(content().string("12"));

    }

    @Test
    public void divideTest() throws Exception {
        RequestBuilder rq = MockMvcRequestBuilders.get("/math/calculate?operation=divide&x=6&y=2");
        mvc.perform(rq)
                .andExpect(status().isOk())
                .andExpect(content().string("3"));

    }

    @Test
    public void defaultAsAddTest() throws Exception {
        RequestBuilder rq = MockMvcRequestBuilders.get("/math/calculate?x=6&y=2");
        mvc.perform(rq)
                .andExpect(status().isOk())
                .andExpect(content().string("8"));

    }

    @Test
    public void sumTest() throws Exception {
        RequestBuilder rq = MockMvcRequestBuilders.post("/math/sum?x=6&y=2&z=10&w=12");
        mvc.perform(rq)
                .andExpect(status().isOk())
                .andExpect(content().string("30"));

    }

    @Test
    public void calculateVolumeTest() throws Exception {

        RequestBuilder rq = MockMvcRequestBuilders.post("/math/volume/3/4/5");
        mvc.perform(rq)
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 3 x 4 x 5 rectangle is 60"));

        rq = MockMvcRequestBuilders.get("/math/volume/5/6/7");
        mvc.perform(rq)
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 5 x 6 x 7 rectangle is 210"));
    }

    @Test
    public void calculateAreaTest() throws Exception {

        MockHttpServletRequestBuilder rq = MockMvcRequestBuilders.post("/math/area")
                .accept(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", "rectangle")
                .param("width", "5")
                .param("height", "10");

        mvc.perform(rq)
                .andExpect(status().isOk())
                .andExpect(content().string("Area of a 5 x 10 rectangle is 50"));

        rq = MockMvcRequestBuilders.post("/math/area")
                .accept(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", "circle")
                .param("radius", "5");

        mvc.perform(rq)
                .andExpect(status().isOk())
                .andExpect(content().string("Area of a circle with a radius of 5 is 15.7080"));

        rq = MockMvcRequestBuilders.post("/math/area")
                .accept(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", "circle")
                .param("width", "5");

        mvc.perform(rq)
                .andExpect(status().isOk())
                .andExpect(content().string("Invalid"));


    }


}
