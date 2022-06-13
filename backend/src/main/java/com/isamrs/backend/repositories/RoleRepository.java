package com.isamrs.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.isamrs.backend.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    
    Role findByName(String name);
    
}
