package com.ra5.projecte3.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.ra5.projecte3.dto.AcademicProfileDTO;
import com.ra5.projecte3.dto.UserRequestDTO;
import com.ra5.projecte3.dto.UserResponseDTO;
import com.ra5.projecte3.model.AcademicProfile;
import com.ra5.projecte3.model.User;

@Component
public class UserMapper {
    
    /**
     * Converteix un User a UserResponseDTO
     * Null-safe: retorna null si l'entrada és null
     * Si user.getAcademicProfile() != null → convertir a AcademicProfileDTO
     */
    public UserResponseDTO toDto(User user) {
        if (user == null) {
            return null;
        }
        
        // Convertir AcademicProfile a AcademicProfileDTO si existeix
        AcademicProfileDTO academicProfileDTO = null;
        if (user.getAcademicProfile() != null) {
            academicProfileDTO = mapAcademicProfileToDTO(user.getAcademicProfile());
        }
        
        return new UserResponseDTO(
            user.getId(),
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.getUsername(),
            user.getRole(),
            user.getDataCreated() != null ? java.sql.Timestamp.valueOf(user.getDataCreated()) : null,
            academicProfileDTO
        );
    }
    
    /**
     * Converteix un UserRequestDTO a User
     * Null-safe: retorna null si l'entrada és null
     * Assigna dataCreated = LocalDateTime.now()
     * Si request.getGrade() != null → crear AcademicProfile i assignar-lo
     */
    public User toEntity(UserRequestDTO request) {
        if (request == null) {
            return null;
        }
        
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        user.setDataCreated(LocalDateTime.now());
        
        // Crear AcademicProfile si grade no és null
        if (request.getGrade() != null) {
            AcademicProfile academicProfile = new AcademicProfile();
            academicProfile.setGrade(request.getGrade());
            
            if (request.getCourse() != null) {
                academicProfile.setCourse(request.getCourse());
            }
            
            if (request.getObservations() != null) {
                academicProfile.setObservations(request.getObservations());
            }
            
            user.setAcademicProfile(academicProfile);
        }
        
        return user;
    }
    
    /**
     * Mètode auxiliar per mappejar AcademicProfile a AcademicProfileDTO
     */
    private AcademicProfileDTO mapAcademicProfileToDTO(AcademicProfile profile) {
        if (profile == null) {
            return null;
        }
        
        return new AcademicProfileDTO(
            profile.getGrade(),
            profile.getCourse(),
            profile.getObservations(),
            profile.getStatus()
        );
    }
}
