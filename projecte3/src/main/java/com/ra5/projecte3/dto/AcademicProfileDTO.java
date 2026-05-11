package com.ra5.projecte3.dto;

public class AcademicProfileDTO {
    
    private String grade;
    private String course;
    private String observations;
    private String status;
    
    // constructors, getters i setters...
    public AcademicProfileDTO() {
    }

    public AcademicProfileDTO(String grade, String course, String observations, String status) {
        this.grade = grade;
        this.course = course;
        this.observations = observations;
        this.status = status;
    }

    public String getGrade() {
        return grade;
    }

    public String getCourse() {
        return course;
    }

    public String getObservations() {
        return observations;
    }

    public String getStatus() {
        return status;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
