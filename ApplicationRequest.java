package com.buspass.studentbuspass.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ApplicationRequest {

    @NotNull(message = "Student ID is required")
    private Long studentId;

    @NotNull(message = "Route ID is required")
    private Long routeId;

    @NotBlank(message = "Boarding point is required")
    private String boardingPoint;

    @NotBlank(message = "Photo reference is required")
    private String photoReference;

    @NotBlank(message = "Registration reason is required")
    private String registrationReason;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public String getBoardingPoint() {
        return boardingPoint;
    }

    public void setBoardingPoint(String boardingPoint) {
        this.boardingPoint = boardingPoint;
    }

    public String getPhotoReference() {
        return photoReference;
    }

    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }

    public String getRegistrationReason() {
        return registrationReason;
    }

    public void setRegistrationReason(String registrationReason) {
        this.registrationReason = registrationReason;
    }
}