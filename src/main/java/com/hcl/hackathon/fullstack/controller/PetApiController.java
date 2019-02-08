package com.hcl.hackathon.fullstack.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.hcl.hackathon.fullstack.model.Pet;
import com.hcl.hackathon.fullstack.services.PetService;
import com.hcl.hackathon.fullstack.util.PetServiceErrorType;

@RestController
@RequestMapping("/api")
public class PetApiController {

	public static final Logger logger = LoggerFactory.getLogger(PetApiController.class);

	@Autowired
	PetService petService;  

	// -------------------Retrieve All pets---------------------------------------------

	@RequestMapping(value = "/pets/", method = RequestMethod.GET)
	public ResponseEntity<List<Pet>> listAllPets() {
		List<Pet> pets = petService.findAllPets();
		if (pets.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Pet>>(pets, HttpStatus.OK);
	}

	// -------------------Retrieve Single Pet------------------------------------------

	@RequestMapping(value = "/pet/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getPet(@PathVariable("id") long id) {
		logger.info("Fetching pet with id {}", id);
		Pet pet = petService.findById(id);
		if (pet == null) {
			logger.error("pet with id {} not found.", id);
			return new ResponseEntity(new PetServiceErrorType("Pet with id " + id 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Pet>(pet, HttpStatus.OK);
	}

	// -------------------Vote Up for pet-------------------------------------------

	@RequestMapping(value = "/pet/{id}/voteUp", method = RequestMethod.GET)
	public ResponseEntity<?> voteUp(@RequestBody Pet pet, UriComponentsBuilder ucBuilder) {
		logger.info("Voting for pet : {}", pet);

		if (!petService.isPetExist(pet)) {
			logger.error("Unable to vote for Pet id "+   pet.getId());
			return new ResponseEntity(new PetServiceErrorType("Unable to  vote"),HttpStatus.CONFLICT);
		}
		petService.voteUp(pet);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/pet/{id}").buildAndExpand(pet.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.ACCEPTED);
	}

	// -------------------Vote down for pet-------------------------------------------

	@RequestMapping(value = "/pet/{id}/voteDown", method = RequestMethod.GET)
	public ResponseEntity<?> voteDown(@RequestBody Pet pet, UriComponentsBuilder ucBuilder) {
		logger.info("Voting for pet : {}", pet);

		if (!petService.isPetExist(pet)) {
			logger.error("Unable to remove vote for Pet id "+   pet.getId());
			return new ResponseEntity(new PetServiceErrorType("Unable to remove vote"),HttpStatus.CONFLICT);
		}
		petService.voteDown(pet);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/pet/{id}").buildAndExpand(pet.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.ACCEPTED);
	}

	// ------------------- Get pets by breed-----------------------------------------
 
	@RequestMapping(value = "/pets/{type}/{breed}", method = RequestMethod.GET)
	public ResponseEntity<List<Pet>> listAllPetsByBreed(@PathVariable("type") String type,@PathVariable("type") String breed) {
		List<Pet> pets = petService.findByBreed(type, breed);
		if (pets.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Pet>>(pets, HttpStatus.OK);
	}

	// ------------------- Get pets by type-----------------------------------------

	@RequestMapping(value = "/pets/{type} ", method = RequestMethod.GET)
	public ResponseEntity<List<Pet>> listAllPetsByType(@PathVariable("type") String type ) {
		List<Pet> pets = petService.findByType(type) ;
		if (pets.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Pet>>(pets, HttpStatus.OK);
	}
}