package com.indytskyi.userservice.controller;


import static org.junit.Assert.assertEquals;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.indytskyi.userservice.controllers.UserController;
import com.indytskyi.userservice.dtos.response.UserResponseDto;
import com.indytskyi.userservice.services.UserService;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTests {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testGetUsersWithAgeGreaterThan() throws Exception {
        // GIVE
        Integer age = 12;
        List<UserResponseDto> users = Arrays.asList(
                new UserResponseDto( 1L,"artem2003@gmail.com",  "Artem", 14, List.of()),
                new UserResponseDto( 2L,"artem2004@gmail.com",  "Maks", 17,List.of()));
        String token = "Bearer yJhbGciOiJIUzI1NiJ9.eyJST0xFIjoiQURNSU4iLCJzdWIiOiJzdXB" +
                "lcmFkbWluQGdtYWlsLmNvbSIsImlhdCI6MTY4MDg3MjI0NSwiZXhwIjoxNjgwODcz" +
                "Njg1fQ.gf5WvPTsmUvdHCR9MDsKfb7-nL6wfzwCxqq3RZNV_Jg";

        //WHEN
        Mockito.when(userService.getAllUsersWithAgeGreaterThan(age)).thenReturn(users);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/users/" + age)
                        .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // THEN
        String responseContent = mvcResult.getResponse().getContentAsString();
        List<UserResponseDto> responseDtoList = new ObjectMapper().readValue(
                responseContent, new TypeReference<List<UserResponseDto>>(){});

        assertEquals(users.size(), responseDtoList.size());
        assertEquals(users.get(0).name(), responseDtoList.get(0).name());
        assertEquals(users.get(1).name(), responseDtoList.get(1).name());
    }

}
