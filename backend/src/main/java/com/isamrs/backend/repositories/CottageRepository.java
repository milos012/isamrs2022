package com.isamrs.backend.repositories;

import com.isamrs.backend.models.Cottage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CottageRepository extends JpaRepository<Cottage, Long>{
    Cottage findCottageByAddress(String address);
    Cottage findCottageByName(String name);
    
    
}
