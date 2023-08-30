package com.epam.talker.service;

import com.epam.talker.repository.UserRepository;
import com.epam.talker.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id " + id + " is not found"));
    }

    public void updateUser(User user, String newName) {
        user.setUsername(newName);
        userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Transactional
    public void followUser(Long followerId, Long followedId) {
        User follower = getUserById(followerId);
        User followed = getUserById(followedId);

        if (follower != null && followed != null) {
            follower.getFollows().add(followed);
            userRepository.save(follower);
        }
    }

    @Transactional
    public void unFollowUser(Long followerId, Long followedId) {
        User follower = getUserById(followerId);
        User followed = getUserById(followedId);

        if (follower != null && followed != null) {
            follower.getFollows().remove(followed);
            userRepository.save(follower);
        }
    }

}
