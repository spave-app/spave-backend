package com.spave.backend.spave.controller;

import com.spave.backend.spave.model.User;
import com.spave.backend.spave.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    void getHelloWorld() throws Exception {
        MvcResult result = mockMvc.perform(get("/hello"))
                        .andDo(print())
                        .andReturn();

       String responseBody = result.getResponse().getContentAsString();

       assertEquals("HELLO WORLD", responseBody);
    }

    @Test
    void getUsers_returns200() throws Exception {
        when(userService.getUsers()).thenReturn(List.of(new User()));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }
}
