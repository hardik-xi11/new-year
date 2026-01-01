package com.hardik.newyear.service;

import com.hardik.newyear.entity.User;
import com.hardik.newyear.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findSubscribedUsers(){
        return userRepository.findBySubscribedTrue();
    }

    public User findUser (String email){
        return userRepository.findByEmail(email);
    }

    public String createUser(String email){

        if(userRepository.findByEmail(email) != null){
            throw new IllegalArgumentException("User with email already exists");
        }

        User newUser = new User();
        newUser.setEmail(email);

        return userRepository.save(newUser).getEmail();
    }

    public User updateUser(String email, String newEmail){
        User user = userRepository.findByEmail(email);

        if(user == null){
            throw new IllegalArgumentException("User not found");
        }

        user.setEmail(newEmail);
        return userRepository.save(user);

    }

    public User deleteUser(String email){
        User user = userRepository.findByEmail(email);
        userRepository.delete(user);
        return user;
    }
}
