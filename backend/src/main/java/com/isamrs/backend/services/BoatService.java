package com.isamrs.backend.services;

import java.util.List;
import java.util.Optional;

import com.isamrs.backend.models.Boat;
import com.isamrs.backend.repositories.BoatRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoatService {

    @Autowired
    public BoatRepository boatRepository;

    public List<Boat> getAllCottages() {
        return boatRepository.findAll();
    }
    
    public Boat getCottageByName(String name){
        return boatRepository.findBoatByName(name);
    }

    public Optional<Boat> getBoatById(Long id){
        return boatRepository.findById(id);
    }

    public Boat addNewBoat(Boat boat){
        return boatRepository.save(boat);
    }

    public boolean deleteBoatById(Long id){
        Optional<Boat> existing = boatRepository.findById(id);
        if(existing.isEmpty()){
            return false;
        }
        boatRepository.delete(existing.get());
        return true;
    }

    public Boat updateCottage(Boat boat){
        //TODO
        return boat;
    }
    
}
