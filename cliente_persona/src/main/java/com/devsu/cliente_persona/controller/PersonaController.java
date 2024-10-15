package com.devsu.cliente_persona.controller;

import com.devsu.cliente_persona.model.Persona;
import com.devsu.cliente_persona.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping
    public ResponseEntity<?> getAllPersonas() {
        List<Persona> personas = personaService.findAll();
        if (personas.isEmpty()) {
            return ResponseEntity.ok("No hay personas en la base de datos");
        }
        return ResponseEntity.ok(personas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> getPersonaById(@PathVariable Long id) {
        Optional<Persona> persona = personaService.findById(id);
        return persona.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Persona createPersona(@RequestBody Persona persona) {
        return personaService.save(persona);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona> updatePersona(@PathVariable Long id, @RequestBody Persona personaDetails) {
        Optional<Persona> persona = personaService.findById(id);
        if (persona.isPresent()) {
            Persona updatedPersona = persona.get();
            updatedPersona.setNombre(personaDetails.getNombre());
            updatedPersona.setGenero(personaDetails.getGenero());
            updatedPersona.setEdad(personaDetails.getEdad());
            updatedPersona.setDireccion(personaDetails.getDireccion());
            updatedPersona.setTelefono(personaDetails.getTelefono());
            return ResponseEntity.ok(personaService.save(updatedPersona));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersona(@PathVariable Long id) {
        if (personaService.findById(id).isPresent()) {
            personaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}