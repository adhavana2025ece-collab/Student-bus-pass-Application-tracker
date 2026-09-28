package com.buspass.studentbuspass.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class ApprovalRequest {

    @NotBlank(message = "Pass number is required")
    private String passNumber;

    @NotNull(message = "Valid from date is required")
    @FutureOrPresent(message = "Valid from date cannot be in the past")
    private LocalDate validFrom;

    @NotNull(message = "Valid until date is required")
    private LocalDate validUntil;

    public String getPassNumber() {
        return passNumber;
    }

    public void setPassNumber(String passNumber) {
        this.passNumber = passNumber;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDate getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDate validUntil) {
        this.validUntil = validUntil;
    }
}