package com.nicola.testproj.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.UUID;

import com.nicola.testproj.model.User;
import com.nicola.testproj.repository.JdbcUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest {

    @Autowired
    private JdbcUserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void testRegister() {
        User user = new User(UUID.randomUUID(),"userName1");
        userService.registerUser(user);
        User registeredUser = userService.findByUsername(user.getUsername());
        assertEquals(user, registeredUser);
    }

    @Test
    public void testLogin() {
        User user = new User(UUID.randomUUID(), "userName2");
        userService.registerUser(user);
        User loggedInUser = userService.findByUsername(user.getUsername());
        assertEquals(user, loggedInUser);
    }

    @Test
    public void testRegister_userAlreadyExists_returnsBadRequest() {
        User user = new User(UUID.randomUUID(), "userName3");
        userService.registerUser(user);
        given(userRepository.findByUsername(user.getUsername()))
                .willReturn(user);

        userService.registerUser(user);
        verify(userRepository).findByUsername(user.getUsername());
    }

    @Test
    public void testLogin_userNotFound_returnsBadRequest() {
        User user = new User(UUID.randomUUID(), "userName4");
        given(userRepository.findByUsername(user.getUsername()))
                .willReturn(null);

        User result = userService.findByUsername(user.getUsername());

        assertThat(result).isNull();
        verify(userRepository).findByUsername(user.getUsername());
    }
}

