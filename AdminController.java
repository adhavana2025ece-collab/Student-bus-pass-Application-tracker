package com.buspass.studentbuspass.controller;

import com.buspass.studentbuspass.dto.ApprovalRequest;
import com.buspass.studentbuspass.dto.RejectionRequest;
import com.buspass.studentbuspass.entity.BusPass;
import com.buspass.studentbuspass.entity.BusPassApplication;
import com.buspass.studentbuspass.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/applications")
    public ResponseEntity<List<BusPassApplication>>
    getApplications() {

        return ResponseEntity.ok(
                adminService.getAllApplications()
        );
    }

    @PutMapping("/applications/{id}/approve")
    public ResponseEntity<BusPass> approveApplication(
            @PathVariable Long id,
            @Valid @RequestBody ApprovalRequest request) {

        return ResponseEntity.ok(
                adminService.approveApplication(id, request)
        );
    }

    @PutMapping("/applications/{id}/reject")
    public ResponseEntity<BusPassApplication> rejectApplication(
            @PathVariable Long id,
            @Valid @RequestBody RejectionRequest request) {

        return ResponseEntity.ok(
                adminService.rejectApplication(id, request)
        );
    }

    @GetMapping("/passes/expiring")
    public ResponseEntity<List<BusPass>> getExpiringPasses() {

        return ResponseEntity.ok(
                adminService.getExpiringPasses()
        );
    }
}