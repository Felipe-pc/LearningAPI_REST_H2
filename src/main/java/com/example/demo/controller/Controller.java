package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Persona;
import com.example.demo.repo.PersonaRepo;

@RestController
@RequestMapping("/personas")
public class Controller {
	
	@Autowired
	private PersonaRepo perRepo;
	
	/**
	 * Get all people list
	 * @return the list
	 */
	
	@GetMapping("/personas")
	public List<Persona> getAll(){
		return perRepo.findAll();
	}
	
	/**
	 * 
	 * @param personaId the person id
	 * @return the users
	 * @throws ResourceNotFoundException
	 */
	
	@GetMapping("/persona/{id}")
	public ResponseEntity<Persona> getById(@PathVariable(value = "id") Long personaId) 
			throws ResourceNotFoundException {
		
		Persona persona = perRepo
				.findById(personaId)
				.orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada en::" + personaId));
		
		return ResponseEntity.ok().body(persona);
	}
	
	/**
	 * Create person
	 * @param per the person
	 * @return the person
	 */
	@PostMapping("/insertPersonas")
	public Persona crearPersona(@Valid @RequestBody Persona per) {
		return perRepo.save(per);
	}
	
	/**
	 * Update Person response Entity
	 * @param personaId the person id
	 * @param perDetalle the person details
	 * @return the response Entity
	 * @throws ResourceNotFoundException the response not found exception
	 */
	
	@PutMapping("/modificarPersona/{id}")
	public ResponseEntity<Persona> updatePer(@PathVariable(value = "id") Long personaId, @Valid @RequestBody Persona perDetalle)
	throws ResourceNotFoundException{
		
		Persona persona = perRepo
				.findById(personaId)
				.orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada en::" + personaId));
		
		persona.setCode(perDetalle.getCode());
		persona.setNombre(perDetalle.getNombre());
		
		final Persona updatePer = perRepo.save(persona);
		return ResponseEntity.ok(updatePer);
		
	}
	
	/**
	 * Delete Person
	 * @param personId the person id
	 * @return the map
	 * @throws Exception the exception
	 */
	
	@DeleteMapping("/borrarPersona/{id}")
	public Map<String, Boolean> deletePerson(@PathVariable(value = "id") Long personId) throws Exception{
		Persona persona = perRepo
				.findById(personId)
				.orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada en::" + personId));
		
		perRepo.delete(persona);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}


