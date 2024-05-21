package com.dbproject.webapp.Services;

import com.dbproject.webapp.Model.User;
import com.dbproject.webapp.Repository.RoleRepository;
import com.dbproject.webapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public User registerNewUser(User user) {
        // Encrypt password and set user role
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRole(roleRepository.findById(2L).orElse(null)); // Assuming 2 is for customers
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}