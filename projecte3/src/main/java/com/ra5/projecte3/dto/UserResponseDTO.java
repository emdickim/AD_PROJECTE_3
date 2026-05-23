package com.ra5.projecte3.dto;

import com.mongodb.lang.Nullable;
import com.ra5.projecte3.model.Role;
import java.sql.Timestamp;

public class UserResponseDTO {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private Role role;
    private Timestamp dataCreated;
    @Nullable
    private AcademicProfileDTO academicProfile;


    public UserResponseDTO() {
    }

    public UserResponseDTO(String id, String firstName, String lastName, String email, String username, Role role,
            Timestamp dataCreated, AcademicProfileDTO academicProfile) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.role = role;
        this.dataCreated = dataCreated;
        this.academicProfile = academicProfile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Timestamp getDataCreated() {
        return dataCreated;
    }

    public void setDataCreated(Timestamp dataCreated) {
        this.dataCreated = dataCreated;
    }

    public AcademicProfileDTO getAcademicProfile() {
        return academicProfile;
    }

    public void setAcademicProfile(AcademicProfileDTO academicProfile) {
        this.academicProfile = academicProfile;
    }

}
