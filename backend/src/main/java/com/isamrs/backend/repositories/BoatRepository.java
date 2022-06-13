package com.isamrs.backend.repositories;

import com.isamrs.backend.models.Boat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoatRepository extends JpaRepository<Boat, Long>{
    Boat findBoatByName(String name);
    
}
