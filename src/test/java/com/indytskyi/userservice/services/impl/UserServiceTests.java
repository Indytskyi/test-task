package com.indytskyi.userservice.services.impl;


import com.indytskyi.userservice.dtos.response.UserResponseDto;
import com.indytskyi.userservice.models.User;
import com.indytskyi.userservice.repository.UserRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    protected static List<User> users;

    @BeforeAll
    static void beforeAll() {
        var userOne = User.builder()
                .id(1L)
                .email("artem2003@gmail.com")
                .name("Artem")
                .age(14)
                .build();

        var userTwo = User.builder()
                .id(1L)
                .email("artem2004@gmail.com")
                .name("Maks")
                .age(17)
                .build();
        users = List.of(userOne, userTwo);
    }

    @Test
    void testIfGetAllUsersWithAgeGreaterTnan() {
        //GIVEN
        Integer age = 25;
        List<UserResponseDto> userResponseDtos = Arrays.asList(
                new UserResponseDto( 1L,"artem2003@gmail.com",  "Artem", 14, List.of()),
                new UserResponseDto( 2L,"artem2004@gmail.com",  "Maks", 17,List.of()));

        //WHEN
        Mockito.when(userRepository.findUserByAgeGreaterThan(age)).thenReturn(users);
        List<UserResponseDto> actual = userService.getAllUsersWithAgeGreaterThan(age);

        Assert.assertEquals(userResponseDtos.size(), actual.size());
        Assert.assertEquals(userResponseDtos.get(0).name(), actual.get(0).name());
        Assert.assertEquals(userResponseDtos.get(1).name(), actual.get(1).name());

    }
}
