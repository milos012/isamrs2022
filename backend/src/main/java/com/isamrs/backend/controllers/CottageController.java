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

import com.isamrs.backend.DTO.CottageDTO;
import com.isamrs.backend.models.Cottage;
import com.isamrs.backend.services.CottageService;

// da li treba ovo produces = mediatype ???

@RestController
@RequestMapping(value = "/api/cottages", produces = MediaType.APPLICATION_JSON_VALUE)
public class CottageController {

    @Autowired
    CottageService cottageService;

    @RequestMapping(value="/all", method = RequestMethod.GET)
	public ResponseEntity<List<CottageDTO>> getAllCottages() {
		
		List<Cottage> cottages = cottageService.getAllCottages();
		
		//convert teachers to DTOs
		List<CottageDTO> cDTO = new ArrayList<>();
		for (Cottage s : cottages) {
			cDTO.add(new CottageDTO(s));
		}
		
		return new ResponseEntity<>(cDTO, HttpStatus.OK);
	}

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<CottageDTO> getCottageId(@PathVariable Long id){
		
		Optional<Cottage> cot = cottageService.getCottageById(id);
		
		if (cot != null) {
			return new ResponseEntity<>(new CottageDTO(cot.get()), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
    
    @RequestMapping(value="/{address}", method=RequestMethod.GET)
	public ResponseEntity<CottageDTO> getCottageAddress(@PathVariable String address){
		
		Cottage cot = cottageService.getCottageByAddress(address);
		
		if (cot != null) {
			return new ResponseEntity<>(new CottageDTO(cot), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

    @RequestMapping(value="/{name}", method=RequestMethod.GET)
	public ResponseEntity<CottageDTO> getCottageByName(@PathVariable String name){
		
		Cottage cot = cottageService.getCottageByName(name);
		
		if (cot != null) {
			return new ResponseEntity<>(new CottageDTO(cot), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

    @RequestMapping(value = "/post", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<CottageDTO> saveCottage(@RequestBody CottageDTO cotDTO) throws Exception {
        Cottage c = new Cottage();
        c.setId(cotDTO.getId());
        c.setAddress(cotDTO.getAddress());
        c.setName(cotDTO.getName());
        c.setDescription(cotDTO.getDescription());
        c.setProfileImage(cotDTO.getProfileImage());
        c.setPrice(cotDTO.getPrice());

        c.setUser(cotDTO.getUser());


    	cottageService.saveCottage(c);
		return new ResponseEntity<>(new CottageDTO(c), HttpStatus.CREATED);
	}

    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ResponseEntity<CottageDTO> deleteCottageId(@PathVariable Long id){
		
		Optional<Cottage> cot = cottageService.getCottageById(id);
		
		if (cot != null) {
            cottageService.deleteCottageById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
