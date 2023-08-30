package com.epam.talker.service;

import com.epam.talker.repository.PostRepository;
import com.epam.talker.repository.UserRepository;
import com.epam.talker.model.Post;
import com.epam.talker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public void createPost(Post post) {
        postRepository.save(post);
    }

    public Post getPostById(Long id) {
       return postRepository.findById(id)
               .orElseThrow(()->new RuntimeException("Post with id "+ id + " is not found"));
    }

    public void updatePost(Long postId, Post newContent) {
        Post source = getPostById(postId);
        source.setTitle(newContent.getTitle());
        source.setBody(newContent.getBody());
        postRepository.save(source);
    }

    public void deletePost(Post post) {
        postRepository.delete(post);
    }

    public void likePost(Long postId, Long userId) {
        Post post = getPostById(postId);
        User user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User with id "+ userId + " is not found"));

        if (post != null && user != null) {
            post.getLikedBy().add(user);
            postRepository.save(post);
        }
    }
}
