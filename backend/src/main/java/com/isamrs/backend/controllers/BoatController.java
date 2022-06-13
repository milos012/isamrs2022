package com.isamrs.backend.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isamrs.backend.DTO.BoatDTO;
import com.isamrs.backend.models.Boat;
import com.isamrs.backend.services.BoatService;


@RestController
@RequestMapping(value = "/api/boats", produces = MediaType.APPLICATION_JSON_VALUE)
public class BoatController {
    
    @Autowired
    BoatService boatService;

    @RequestMapping(value="/all", method = RequestMethod.GET)
	public ResponseEntity<List<BoatDTO>> getAllBoats() {
		
		List<Boat> boats = boatService.getAllBoats();
		
		//convert teachers to DTOs
		List<BoatDTO> bDTO = new ArrayList<>();
		for (Boat b : boats) {
			bDTO.add(new BoatDTO(b));
		}
		
		return new ResponseEntity<>(bDTO, HttpStatus.OK);
	}

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<BoatDTO> getBoatById(@PathVariable Long id){
		
		Optional<Boat> boat = boatService.getBoatById(id);
		
		if (boat != null) {
			return new ResponseEntity<>(new BoatDTO(boat.get()), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

    @RequestMapping(value="/{name}", method=RequestMethod.GET)
	public ResponseEntity<BoatDTO> getBoatByName(@PathVariable String name){
		
		Boat boat = boatService.getBoatByName(name);
		
		if (boat != null) {
			return new ResponseEntity<>(new BoatDTO(boat), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

    @RequestMapping(value = "/post", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<BoatDTO> saveCottage(@RequestBody BoatDTO bDTO) throws Exception {

        Boat b = new Boat();
        b.setId(bDTO.getId());
        b.setName(bDTO.getName());
        b.setDescription(bDTO.getDescription());
        b.setProfileImage(bDTO.getProfileImage());
        b.setPricePerHr(bDTO.getPricePerHr());
        b.setDriver(bDTO.isDriver());

        b.setUser(bDTO.getUser());



    	boatService.saveBoat(b);
		return new ResponseEntity<>(new BoatDTO(b), HttpStatus.CREATED);
	}

    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ResponseEntity<BoatDTO> deleteBoatById(@PathVariable Long id){
		
		Optional<Boat> boat = boatService.getBoatById(id);
		
		if (boat != null) {
            boatService.deleteBoatById(id);
			return new ResponseEntity<>(new BoatDTO(boat.get()),HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
