package com.hcl.hackathon.fullstack.services;

import java.util.List;

import com.hcl.hackathon.fullstack.model.Pet;

public interface PetService {

	Pet findById(Long id);

	void savePet(Pet pet);

	void updatePet(Pet pet);

	void deletePetById(Long id);

	void deleteAllPets();

	List<Pet> findAllPets();

	List<Pet>  findAllDogs();
	
	List<Pet>  findAllDogsByBreed(String breed);

	List<Pet> findByType(String type);

	List<Pet> findByBreed(String type,String breed);

	void voteUp(Pet pet);

	void voteDown(Pet pet);

	boolean isPetExist(Pet pet);
}