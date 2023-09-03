package com.epam.talker.service;
import com.epam.talker.model.Post;
import com.epam.talker.model.User;
import com.epam.talker.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Optional;

@SpringBootTest
@WebAppConfiguration
@ContextConfiguration
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("galina");
        userService.createUser(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testGetUserById() {
        Long userId = 1L;
        User user = new User();
        user.setUsername("galina");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        User result = userService.getUserById(userId);
        assertNotNull(result);
        assertEquals(user, result);
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("galina");
        String newName = "NewName";
        userService.updateUser(user, newName);

        verify(userRepository, times(1)).save(user);
        assertEquals(newName, user.getUsername());
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("galina");
        userService.deleteUser(user);
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    public void testFollowUser() {
        Long followerId = 1L;
        Long followedId = 2L;
        User follower = new User();
        follower.setId(followerId);
        follower.setUsername("galina");
        User followed = new User();
        followed.setId(followedId);
        followed.setUsername("andrey");

        when(userRepository.findById(followerId)).thenReturn(Optional.of(follower));
        when(userRepository.findById(followedId)).thenReturn(Optional.of(followed));

        userService.followUser(followerId, followedId);

        verify(userRepository, times(1)).save(follower);
        assertTrue(follower.getFollows().contains(followed));
    }

    @Test
    public void testUnFollowUser() {
        Long followerId = 1L;
        Long followedId = 2L;
        User follower = new User();
        follower.setId(followerId);
        follower.setUsername("galina");
        User followed = new User();
        followed.setId(followedId);
        followed.setUsername("andrey");
        follower.getFollows().add(followed);

        when(userRepository.findById(followerId)).thenReturn(Optional.of(follower));
        when(userRepository.findById(followedId)).thenReturn(Optional.of(followed));

        userService.unFollowUser(followerId, followedId);

        verify(userRepository, times(1)).save(follower);
        assertFalse(follower.getFollows().contains(followed));
    }
}

