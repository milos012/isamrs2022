package com.isamrs.backend.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<List<CottageDTO>> getAllUsers() {
		
		List<Cottage> cottages = cottageService.getAllCottages();
		
		//convert teachers to DTOs
		List<CottageDTO> cDTO = new ArrayList<>();
		for (Cottage s : cottages) {
			cDTO.add(new CottageDTO(s));
		}
		
		return new ResponseEntity<>(cDTO, HttpStatus.OK);
	}
    
}
