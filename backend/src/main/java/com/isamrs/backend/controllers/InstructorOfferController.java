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

import com.isamrs.backend.DTO.InstructorOfferDTO;
import com.isamrs.backend.models.InstructorOffer;
import com.isamrs.backend.services.InstructorOfferService;


// da li treba ovo produces = mediatype ???

@RestController
@RequestMapping(value = "/api/instructoroffers", produces = MediaType.APPLICATION_JSON_VALUE)
public class InstructorOfferController {

    @Autowired
    InstructorOfferService ioService;

    @RequestMapping(value="/all", method = RequestMethod.GET)
	public ResponseEntity<List<InstructorOfferDTO>> getAllInstructorOffers() {
		
		List<InstructorOffer> ioffers = ioService.getAllInstructorOffers();
		
		//convert teachers to DTOs
		List<InstructorOfferDTO> ioDTO = new ArrayList<>();
		for (InstructorOffer io : ioffers) {
			ioDTO.add(new InstructorOfferDTO(io));
		}
		
		return new ResponseEntity<>(ioDTO, HttpStatus.OK);
	}

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<InstructorOfferDTO> getInstructorOfferById(@PathVariable Long id){
		
		Optional<InstructorOffer> io = ioService.getInstructorOfferById(id);
		
		if (io != null) {
			return new ResponseEntity<>(new InstructorOfferDTO(io.get()), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

    @RequestMapping(value="/{offerName}", method=RequestMethod.GET)
	public ResponseEntity<InstructorOfferDTO> getInstructorOfferByName(@PathVariable String name){
		
		InstructorOffer io = ioService.getInstructorOfferByName(name);
		
		if (io != null) {
			return new ResponseEntity<>(new InstructorOfferDTO(io), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ResponseEntity<InstructorOfferDTO> deleteInstructorOfferById(@PathVariable Long id){
		
		Optional<InstructorOffer> io = ioService.getInstructorOfferById(id);
		
		if (io != null) {
            ioService.deleteInstructorOfferById(id);
			return new ResponseEntity<>(new InstructorOfferDTO(io.get()),HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

    @RequestMapping(value = "/post", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<InstructorOfferDTO> saveInstructorOffer(@RequestBody InstructorOfferDTO ioDTO) throws Exception {

        InstructorOffer io = new InstructorOffer();
        io.setId(ioDTO.getId());
        io.setOfferName(ioDTO.getOfferName());
        io.setDescription(ioDTO.getDescription());
        io.setProfileImage(ioDTO.getProfileImage());
        io.setPricePerHr(ioDTO.getPricePerHr());

        io.setUser(ioDTO.getUser());


    	ioService.saveInstructorOffer(io);
		return new ResponseEntity<>(new InstructorOfferDTO(io), HttpStatus.CREATED);
	}
    
}
