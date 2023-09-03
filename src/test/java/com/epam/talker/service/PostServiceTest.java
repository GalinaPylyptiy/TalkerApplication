package com.epam.talker.service;
import com.epam.talker.model.Post;
import com.epam.talker.model.User;
import com.epam.talker.repository.PostRepository;
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
public class PostServiceTest {
    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PostService postService;

    @Test
    public void testCreatePost() {
        Post post = new Post(); // Create a post instance
        postService.createPost(post);
        verify(postRepository, times(1)).save(post);
    }

    @Test
    public void testGetPostById() {
        Long postId = 1L;
        Post post = new Post(); // Create a post instance
        post.setId(postId);
        post.setTitle("Hello");
        post.setBody("Hello, everyone");

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        Post result = postService.getPostById(postId);

        assertNotNull(result);
        assertEquals(post, result);
    }

    @Test
    public void testUpdatePost() {
        Long postId = 1L;
        Post existingPost = new Post();
        existingPost.setId(postId);
        existingPost.setTitle("Hello");
        existingPost.setBody("Hello, everyone");
        Post newContent = new Post();
        newContent.setId(postId);
        newContent.setTitle("Goodbye");
        newContent.setBody("GoodBye, everyone");

        when(postRepository.findById(postId)).thenReturn(Optional.of(existingPost));
        postService.updatePost(postId, newContent);

        verify(postRepository, times(1)).save(existingPost);
        assertEquals(newContent.getTitle(), existingPost.getTitle());
        assertEquals(newContent.getBody(), existingPost.getBody());
    }

    @Test
    public void testDeletePost() {
        Post post = new Post(); // Create a post instance
        post.setId(1L);
        post.setTitle("Goodbye");
        post.setBody("GoodBye, everyone");
        postService.deletePost(post);
        verify(postRepository, times(1)).delete(post);
    }

    @Test
    public void testLikePost() {
        Long postId = 1L;
        Long userId = 2L;
        Post post = new Post();
        post.setId(postId);
        post.setTitle("Goodbye");
        post.setBody("GoodBye, everyone");
        User user = new User();
        user.setId(userId);
        user.setUsername("galina");

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        postService.likePost(postId, userId);

        verify(postRepository, times(1)).save(post);
        assertTrue(post.getLikedBy().contains(user));
    }
}
