package com.example.TaskManager.services;

import com.example.TaskManager.entities.User;
import com.example.TaskManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.NonUniqueResultException;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //save a new user
    public void saveUser(User user) {
        try {
            // Validate the password before saving the user
            if (!isValidPassword(user.getPassword())) {
                throw new IllegalArgumentException("Invalid password format.");
            }
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Username already exists.");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    //retrieve an user
    public User getUser(User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    //get the details of the user by username
    public boolean getUserByUsername(String username, String password) {
        try {
            // Validate the password format
            if (!isValidPassword(password)) {
                throw new IllegalArgumentException("Invalid password format.");
            }

            boolean username_present;
            boolean password_present;

            username_present = userRepository.findTopByUsername(username) != null;
            System.out.println("Username present: " + username_present);
            password_present = userRepository.findTopByPassword(password) != null;
            System.out.println("Password present: " + password_present);

            return username_present && password_present;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (NonUniqueResultException nre) {
            throw new RuntimeException("Multiple users found with the same username.");
        }
    }

    //find user by username
    public boolean findUserByUsername(String username) {
        try {
            boolean username_present = userRepository.findTopByUsername(username) != null;
            System.out.println("Username present (U): " + username_present);
            return username_present;
        } catch (NonUniqueResultException nre) {
            throw new RuntimeException("Multiple users found with the same username.");
        }
    }

    // Password validation function
    private boolean isValidPassword(String password) {
        if (password == null || password.isEmpty() || password.length() < 8 || password.length() > 12) {
            return false;
        }

        // Check for at least one capital letter, one number, one symbol, and simple letters
        Pattern pattern = Pattern.compile("^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*(),.?\":{}|<>]).*$");
        return pattern.matcher(password).matches();
    }


}
