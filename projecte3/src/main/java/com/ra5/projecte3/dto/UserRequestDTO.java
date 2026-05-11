package com.ra5.projecte3.dto;

import java.util.Optional;

import com.mongodb.lang.Nullable;
import com.ra5.projecte3.model.Role;

public class UserRequestDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private Role role;

    //poden ser optionals
    
    private Optional<String> grade;
    
    private Optional<String> course;

    private Optional<String> observations;


    public UserRequestDTO() {
    }


    public UserRequestDTO(String firstName, String lastName, String email, String username, String password,
            Role role, String grade, String course, String observations) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.grade = Optional.ofNullable(grade);
        this.course = Optional.ofNullable(course);
        this.observations = Optional.ofNullable(observations);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Optional<String> getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = Optional.ofNullable(grade);
    }

    public Optional<String>getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = Optional.ofNullable(course);
    }

    public Optional<String> getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = Optional.ofNullable(observations);
    }

}
