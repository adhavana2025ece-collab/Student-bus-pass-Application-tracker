package com.buspass.studentbuspass.controller;

import com.buspass.studentbuspass.dto.ApplicationRequest;
import com.buspass.studentbuspass.entity.BusPassApplication;
import com.buspass.studentbuspass.service.BusPassApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "*")
public class BusPassApplicationController {

    private final BusPassApplicationService applicationService;

    public BusPassApplicationController(
            BusPassApplicationService applicationService) {

        this.applicationService = applicationService;
    }

    @PostMapping
    public ResponseEntity<BusPassApplication> submitApplication(
            @Valid @RequestBody ApplicationRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        applicationService.submitApplication(request)
                );
    }

    @GetMapping
    public ResponseEntity<List<BusPassApplication>> getAllApplications() {

        return ResponseEntity.ok(
                applicationService.getAllApplications()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusPassApplication> getApplication(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                applicationService.getApplicationById(id)
        );
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<BusPassApplication>>
    getStudentApplications(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                applicationService
                        .getApplicationsByStudent(studentId)
        );
    }
}