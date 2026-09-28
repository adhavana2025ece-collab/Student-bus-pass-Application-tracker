package com.buspass.studentbuspass.service;

import com.buspass.studentbuspass.dto.ApprovalRequest;
import com.buspass.studentbuspass.dto.RejectionRequest;
import com.buspass.studentbuspass.entity.ApplicationStatus;
import com.buspass.studentbuspass.entity.BusPass;
import com.buspass.studentbuspass.entity.BusPassApplication;
import com.buspass.studentbuspass.exception.BusinessRuleException;
import com.buspass.studentbuspass.exception.ResourceNotFoundException;
import com.buspass.studentbuspass.repository.BusPassApplicationRepository;
import com.buspass.studentbuspass.repository.BusPassRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdminService {

    private final BusPassApplicationRepository applicationRepository;
    private final BusPassRepository passRepository;

    public AdminService(
            BusPassApplicationRepository applicationRepository,
            BusPassRepository passRepository) {

        this.applicationRepository = applicationRepository;
        this.passRepository = passRepository;
    }

    public BusPass approveApplication(
            Long applicationId,
            ApprovalRequest request) {

        BusPassApplication application =
                applicationRepository.findById(applicationId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Application with ID "
                                                + applicationId
                                                + " not found"
                                )
                        );

        // Rule: only PENDING can be approved
        if (application.getStatus()
                != ApplicationStatus.PENDING) {

            throw new BusinessRuleException(
                    "Only pending applications can be approved"
            );
        }

        if (request.getValidUntil()
                .isBefore(request.getValidFrom())) {

            throw new BusinessRuleException(
                    "Valid until date must be after valid from date"
            );
        }

        if (passRepository.existsByApplicationStudentIdAndValidUntilGreaterThanEqual(
                application.getStudent().getId(),
                LocalDate.now())) {

            throw new BusinessRuleException(
                    "Student already has an active bus pass"
            );
        }

        BusPass pass = new BusPass();

        pass.setApplication(application);
        pass.setPassNumber(request.getPassNumber());
        pass.setValidFrom(request.getValidFrom());
        pass.setValidUntil(request.getValidUntil());

        application.setStatus(ApplicationStatus.APPROVED);
        application.setAdminRemark("Application approved");

        applicationRepository.save(application);

        return passRepository.save(pass);
    }

    public BusPassApplication rejectApplication(
            Long applicationId,
            RejectionRequest request) {

        BusPassApplication application =
                applicationRepository.findById(applicationId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Application with ID "
                                                + applicationId
                                                + " not found"
                                )
                        );

        // Rule: only PENDING can be rejected
        if (application.getStatus()
                != ApplicationStatus.PENDING) {

            throw new BusinessRuleException(
                    "Only pending applications can be rejected"
            );
        }

        application.setStatus(ApplicationStatus.REJECTED);
        application.setAdminRemark(request.getRemark());

        return applicationRepository.save(application);
    }
    public List<BusPassApplication> getAllApplications() {
        return applicationRepository.findAll();
    }
    public List<BusPass> getExpiringPasses() {

        LocalDate today = LocalDate.now();

        LocalDate thirtyDaysLater =
                today.plusDays(30);

        return passRepository.findByValidUntilBetween(
                today,
                thirtyDaysLater
        );
    }
}