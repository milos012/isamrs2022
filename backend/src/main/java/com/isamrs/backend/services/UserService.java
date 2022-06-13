package com.isamrs.backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.isamrs.backend.models.Role;
import com.isamrs.backend.models.User;
import com.isamrs.backend.repositories.RoleRepository;
import com.isamrs.backend.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public RoleRepository roleRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email);

    }

    public Optional<User> findUserById(Long id){
        return userRepository.findById(id);
    }

    public List<User> getUsersByRole(Role role){
        List<User> neededusers = new ArrayList<User>();
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getRole().equals(role)){
                neededusers.add(user);
            }
        }
        return neededusers;
    }

    public User saveUser(User u){
        return userRepository.save(u);
    }

    public boolean deleteUserById(Long id){
        Optional<User> existing = userRepository.findById(id);
        if(existing.isEmpty()){
            return false;
        }
        userRepository.delete(existing.get());
        return true;
    }

    public User updateUser(User u){
        //TODO
        return u;
    }

    
}
