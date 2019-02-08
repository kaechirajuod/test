package com.hcl.hackathon.fullstack.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.hackathon.fullstack.model.Pet;
import com.hcl.hackathon.fullstack.repository.PetRepository;

@Service("petService")
@Transactional
public class PetServiceImpl implements PetService {

	@Autowired
	private PetRepository petRepository;

	public Pet findById(Long id) {
		return petRepository.getOne(id);
	}

	public void savePet(Pet pet) {
		petRepository.saveAndFlush(pet);
	}

	public void updatePet(Pet pet) {
		savePet(pet);
	}

	public void deletePetById(Long id) {
		petRepository.deleteById(id);
	}

	public void deleteAllPets() {
		petRepository.deleteAll();
	}

	public List<Pet> findAllPets() {
		return petRepository.findAll();
	}

	public boolean isPetExist(Pet dog) {
		return dog.getId() != 0;
	}

	public void voteUp(Pet pet) {
		pet.setVotes(pet.getVotes() + 1);
		savePet(pet);
	}

	public void voteDown(Pet pet) {
		Integer votes = pet.getVotes();
		if (votes != 0) {
			pet.setVotes(pet.getVotes() - 1);
		}
		savePet(pet);
	}

	public List<Pet> findAllDogs() {
		return findByType("DOG");
	}

	public List<Pet> findAllDogsByBreed(String breed) {
		return findByBreed("DOG", breed);
	}

	public List<Pet> findByType(String type) {
		if (type == null || type.isEmpty()) {
			return new ArrayList<Pet>();
		}

		// TODO Validate type

		return petRepository.findByTypeOrderByVotesDesc(type);

	}

	public List<Pet> findByBreed(String type, String breed) {
		if (breed == null || breed.isEmpty()) {
			return new ArrayList<Pet>();
		}
		if (type == null || type.isEmpty()) {
			return new ArrayList<Pet>();
		}

		// TODO Validate type && breed

		return petRepository.findByBreedOrderByVotesDesc( breed);
	}

}
