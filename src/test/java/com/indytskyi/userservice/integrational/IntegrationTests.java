package com.indytskyi.userservice.integrational;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.indytskyi.userservice.dtos.RegisterRequestDto;
import com.indytskyi.userservice.repository.UserRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
class IntegrationTests  {

    private static PostgreSQLContainer<?> container =new PostgreSQLContainer<>("postgres:latest");;

    @Autowired
    protected ObjectMapper objectMapper;
    protected MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WebApplicationContext context;

    private RegisterRequestDto registerRequestDto;


    static {
        container.start();
    }

    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        registerRequestDto = RegisterRequestDto.builder()
                .name("Artem")
                .email("artem2003@gmail.com")
                .password("Artem2003@")
                .age(21)
                .build();
    }

    @Test
    @SneakyThrows
    void registerWithValidData() {
        //WHEN
        mockMvc.perform(post("/register")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(registerRequestDto)))
                .andExpect(status().isCreated());

        //THEN
        Assertions.assertTrue(userRepository.findByEmail(registerRequestDto.getEmail()).isPresent());
    }

    @Test
    @SneakyThrows
    void registerWithNotMatchPassword() {
        //GIVEN
        String expected = "Incorrect format of password";
        registerRequestDto.setPassword("password");

        //WHEN
        String actual = mockMvc.perform(post("/register")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(registerRequestDto)))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();

        //THEN
        Assertions.assertTrue(actual.contains(expected));
    }

}
