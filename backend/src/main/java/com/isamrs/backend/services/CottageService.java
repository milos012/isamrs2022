package com.isamrs.backend.services;

import java.util.List;
import java.util.Optional;

import com.isamrs.backend.models.Cottage;
import com.isamrs.backend.repositories.CottageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CottageService {

    @Autowired
    public CottageRepository cottageRepository;

    public List<Cottage> getAllCottages() {
        return cottageRepository.findAll();
    }
    
    public Cottage getCottageByAddress(String address){
        return cottageRepository.findCottageByAddress(address);
    }
    
    public Cottage getCottageByName(String name){
        return cottageRepository.findCottageByName(name);
    }

    public Optional<Cottage> getCottageById(Long id){
        return cottageRepository.findById(id);
    }

    public Cottage saveCottage(Cottage cot){
        return cottageRepository.save(cot);
    }

    public boolean deleteCottageById(Long id){
        Optional<Cottage> existing = cottageRepository.findById(id);
        if(existing.isEmpty()){
            return false;
        }
        cottageRepository.delete(existing.get());
        return true;
    }

    public Cottage updateCottage(Cottage cot){
        //TODO
        return cot;
    }
}
