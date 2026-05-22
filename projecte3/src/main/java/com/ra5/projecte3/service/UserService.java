package com.ra5.projecte3.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ra5.projecte3.dto.UserRequestDTO;
import com.ra5.projecte3.dto.UserResponseDTO;
import com.ra5.projecte3.mapper.UserMapper;
import com.ra5.projecte3.model.Role;
import com.ra5.projecte3.model.User;
import com.ra5.projecte3.repository.UserRepository;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    
    // Injecció de dependències per constructor
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    
    /**
     * Crea un nou usuari
     * - Verifica que l'email no existeixi ja (retorna null si existeix)
     * - Converteix a entitat amb el Mapper
     * - Guarda amb userRepository.save()
     * - Retorna el DTO de l'entitat guardada (amb l'id generat per Mongo)
     */
    public UserResponseDTO create(UserRequestDTO request) {
        if (request == null) {
            return null;
        }
        
        // Verificar que l'email no existeix ja
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return null; // Email ja existeix
        }
        
        // Convertir DTO a entitat
        User user = userMapper.toEntity(request);
        
        // Guardar a MongoDB
        User savedUser = userRepository.save(user);
        
        // Retornar el DTO de l'entitat guardada
        return userMapper.toDto(savedUser);
    }
    
    /**
     * Actualitza un usuari existent
     * - Verifica que l'usuari existeix (retorna null si no)
     * - Actualitza els camps (respecta el dataCreated original)
     * - Guarda i retorna el DTO actualitzat
     */
    public UserResponseDTO update(String id, UserRequestDTO request) {
        if (id == null || request == null) {
            return null;
        }
        
        // Verificar que l'usuari existeix
        Optional<User> existingUser = userRepository.findById(id);
        if (!existingUser.isPresent()) {
            return null; // L'usuari no existeix
        }
        
        User user = existingUser.get();
        
        // Actualitzar els camps (mantenint el dataCreated original)
        LocalDateTime originalDataCreated = user.getDataCreated();
        
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        
        // Respectar el dataCreated original
        user.setDataCreated(originalDataCreated);
        
        // Actualitzar AcademicProfile si es proporciona
        if (request.getGrade() != null) {
            var academicProfile = user.getAcademicProfile();
            if (academicProfile == null) {
                academicProfile = new com.ra5.projecte3.model.AcademicProfile();
            }
            
            academicProfile.setGrade(request.getGrade());
            if (request.getCourse() != null) {
                academicProfile.setCourse(request.getCourse());
            }
            if (request.getObservations() != null) {
                academicProfile.setObservations(request.getObservations());
            }
            
            user.setAcademicProfile(academicProfile);
        }
        
        // Guardar i retornar el DTO actualitzat
        User updatedUser = userRepository.save(user);
        return userMapper.toDto(updatedUser);
    }
    
    /**
     * Elimina un usuari
     * - Retorna false si no existeix
     * - Esborra i retorna true si s'ha eliminat correctament
     */
    public boolean delete(String id) {
        if (id == null) {
            return false;
        }
        
        // Verificar que l'usuari existeix
        if (!userRepository.existsById(id)) {
            return false; // L'usuari no existeix
        }
        
        // Esborrar i retornar true
        userRepository.deleteById(id);
        return true;
    }
    
    /**
     * Retorna tots els usuaris convertits a UserResponseDTO
     * Utilitza Stream per convertir els User a UserResponseDTO
     */
    public List<UserResponseDTO> findAll() {
        return userRepository.findAll()
            .stream()
            .map(userMapper::toDto)
            .collect(Collectors.toList());
    }
    
    /**
     * Busca un usuari per id
     * - Retorna UserResponseDTO si existeix
     * - Retorna null si no existeix
     */
    public UserResponseDTO findById(String id) {
        if (id == null) {
            return null;
        }
        
        Optional<User> user = userRepository.findById(id);
        return user.map(userMapper::toDto).orElse(null);
    }
    
    /**
     * Busca usuaris per rol
     * - Retorna una llista de UserResponseDTO amb aquell rol
     * - Retorna una llista buida si no n'hi ha
     */
    public List<UserResponseDTO> findByRole(Role role) {
        if (role == null) {
            return List.of();
        }
        
        return userRepository.findByRole(role)
            .stream()
            .map(userMapper::toDto)
            .collect(Collectors.toList());
    }
    
    /**
     * Busca un usuari per username
     * - Retorna UserResponseDTO si existeix
     * - Retorna null si no existeix
     */
    public UserResponseDTO findByUsername(String username) {
        if (username == null) {
            return null;
        }
        
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(userMapper::toDto).orElse(null);
    }
}
