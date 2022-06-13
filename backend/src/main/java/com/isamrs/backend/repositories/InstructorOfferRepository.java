package com.isamrs.backend.repositories;

import com.isamrs.backend.models.InstructorOffer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorOfferRepository extends JpaRepository<InstructorOffer, Long>{
    InstructorOffer findInstructorOfferByName(String offerName);
    
}
