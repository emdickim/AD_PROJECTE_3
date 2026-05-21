package com.ra5.projecte3.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ra5.projecte3.dto.UserRequestDTO;
import com.ra5.projecte3.dto.UserResponseDTO;
import com.ra5.projecte3.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final UserService userService;
    
    // Injecció de dependències per constructor
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    /**
     * POST /api/users
     * Crea un nou usuari
     * @param request UserRequestDTO amb les dades del nou usuari
     * @return 201 CREATED amb UserResponseDTO, o 409 CONFLICT si l'email ja existeix
     */
    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserRequestDTO request) {
        UserResponseDTO response = userService.create(request);
        
        if (response == null) {
            // Email ja existeix
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    /**
     * PUT /api/users/{id}
     * Actualitza un usuari existent
     * @param id ID de l'usuari a actualitzar
     * @param request UserRequestDTO amb les noves dades
     * @return 200 OK amb UserResponseDTO actualitzat, o 404 NOT FOUND
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable String id, @RequestBody UserRequestDTO request) {
        UserResponseDTO response = userService.update(id, request);
        
        if (response == null) {
            // L'usuari no existeix
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * DELETE /api/users/{id}
     * Elimina un usuari
     * @param id ID de l'usuari a eliminar
     * @return 204 NO CONTENT si s'ha eliminat correctament, o 404 NOT FOUND
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        boolean deleted = userService.delete(id);
        
        if (!deleted) {
            // L'usuari no existeix
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.noContent().build();
    }
}
