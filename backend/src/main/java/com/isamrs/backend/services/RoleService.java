package com.isamrs.backend.services;

import java.util.List;

import com.isamrs.backend.models.Role;
import com.isamrs.backend.repositories.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    
    @Autowired
    public RoleRepository roleRepository;

    public Role getRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    public List<Role> findAll(){
        return roleRepository.findAll();
    }
    
}
