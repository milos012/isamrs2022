package com.isamrs.backend.services;

import java.util.List;
import java.util.Optional;

import com.isamrs.backend.models.InstructorOffer;
import com.isamrs.backend.repositories.InstructorOfferRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorOfferService {
    
    @Autowired
    public InstructorOfferRepository instructorOfferRepository;

    public List<InstructorOffer> getAllInstructorOffers() {
        return instructorOfferRepository.findAll();
    }
    
    public InstructorOffer getInstructorOfferByName(String name){
        return instructorOfferRepository.findInstructorOfferByName(name);
    }

    public Optional<InstructorOffer> getInstructorOfferById(Long id){
        return instructorOfferRepository.findById(id);
    }

    public InstructorOffer saveInstructorOffer(InstructorOffer ioff){
        return instructorOfferRepository.save(ioff);
    }

    public boolean deleteInstructorOfferById(Long id){
        Optional<InstructorOffer> existing = instructorOfferRepository.findById(id);
        if(existing.isEmpty()){
            return false;
        }
        instructorOfferRepository.delete(existing.get());
        return true;
    }

    public InstructorOffer updateInstructorOffer(InstructorOffer ioff){
        //TODO
        return ioff;
    }
    
}
