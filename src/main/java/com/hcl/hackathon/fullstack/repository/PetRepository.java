package com.hcl.hackathon.fullstack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.hackathon.fullstack.model.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

 	List<Pet> findByTypeOrderByVotesDesc(String type);

 	List<Pet> findByBreedOrderByVotesDesc(String breed);

}
