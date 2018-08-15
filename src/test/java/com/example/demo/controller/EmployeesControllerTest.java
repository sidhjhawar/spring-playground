package com.example.demo.controller;

import com.example.demo.config.SecurityConfig;
import com.example.demo.crud.EmployeeRepository;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@Import(SecurityConfig.class)
public class EmployeesControllerTest {

    @MockBean
    EmployeeRepository repository;

    @Autowired
    MockMvc mvc;

    @Test
    @WithMockUser(username="admin",roles={"MANAGER"})
    public void getEmployeeList() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/admin/employees")
                .secure(true)
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getEmployeeListWontWork() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/admin/employees").with(SecurityMockMvcRequestPostProcessors.anonymous())
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
}