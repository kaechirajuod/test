package com.hcl.hackathon.fullstack;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.hcl.hackathon.fullstack.model.Pet;
import com.hcl.hackathon.fullstack.services.PetService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class FullStackApplicationTests {
	
	
	@Autowired
    PetService petService;
    
    
   @Test
   public void  findAllPets() {
  	List<Pet> findAllPets = petService.findAllPets();
  	assertFalse(findAllPets.isEmpty()); 
   }
    
    @Test
    public void  findByType() {
    	List<Pet> findAllPets = petService.findByType("DOG");
    	assertFalse(findAllPets.isEmpty()); 
    	 findAllPets = petService.findByType("CAT");
    	 assertTrue(findAllPets.isEmpty());
    }
    
    @Test
    public void  findByBreed() {
  	List<Pet> findAllPets = petService.findByBreed("DOG", "Labrador");
   	assertFalse(findAllPets.isEmpty()); 
   }

	@Test
	public void contextLoads() {
	}

}
