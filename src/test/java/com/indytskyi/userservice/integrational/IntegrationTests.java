package com.indytskyi.userservice.integrational;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.indytskyi.userservice.dtos.request.AuthenticationRequestDto;
import com.indytskyi.userservice.dtos.request.RegisterRequestDto;
import com.indytskyi.userservice.dtos.response.AuthenticationResponse;
import com.indytskyi.userservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@Transactional
@ActiveProfiles("test")
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
        registry.add("DB_URL", container::getJdbcUrl);
        registry.add("DB_USERNAME", container::getUsername);
        registry.add("DB_PASSWORD", container::getPassword);
    }

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        registerRequestDto = RegisterRequestDto.builder()
                .name("Artem")
                .email("ind.artem2003@gmail.com")
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

    @Test
    @Sql(value = "/add_users.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/add_articles.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @SneakyThrows
    void testFindNamesOfUsersWithMoreThan3ArticlesWithCorrectJwtToken() {
        //GIVEN
        var expected = "[\"Artem\"]";
        var authenticationRequestDto = new AuthenticationRequestDto(
                "superadmin@gmail.com",
                "Artem2003@");
        //WHEN
        //login user
        String response =  mockMvc.perform(post("/login")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(authenticationRequestDto)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        AuthenticationResponse authenticationResponse = objectMapper.readValue(response, AuthenticationResponse.class);


        String actual = mockMvc.perform(get("/users/big-publishers")
                        .header("Authorization", "Bearer " + authenticationResponse.token()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(expected, actual);
    }

    @Test
    @Sql(value = "/add_users.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @SneakyThrows
    void testFindNamesOfUsersWithMoreThan3ArticlesWithUserRole() {
        //GIVEN
        var expected = "User don`t have access to this request";
        var authenticationRequestDto = new AuthenticationRequestDto(
                "ind.artem2003@gmail.com",
                "Artem2003@");
        //WHEN
        //login user
        String response =  mockMvc.perform(post("/login")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(authenticationRequestDto)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        AuthenticationResponse authenticationResponse = objectMapper.readValue(response, AuthenticationResponse.class);


        mockMvc.perform(get("/users/big-publishers")
                        .header("Authorization", "Bearer " + authenticationResponse.token()))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(expected));
    }
}
