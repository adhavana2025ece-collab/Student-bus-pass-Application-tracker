package com.buspass.studentbuspass.service;

import com.buspass.studentbuspass.dto.ApplicationRequest;
import com.buspass.studentbuspass.entity.ApplicationStatus;
import com.buspass.studentbuspass.entity.BusPassApplication;
import com.buspass.studentbuspass.entity.BusRoute;
import com.buspass.studentbuspass.entity.Student;
import com.buspass.studentbuspass.exception.BusinessRuleException;
import com.buspass.studentbuspass.exception.ResourceNotFoundException;
import com.buspass.studentbuspass.repository.BusPassApplicationRepository;
import com.buspass.studentbuspass.repository.BusPassRepository;
import com.buspass.studentbuspass.repository.BusRouteRepository;
import com.buspass.studentbuspass.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BusPassApplicationService {

    private final BusPassApplicationRepository applicationRepository;
    private final StudentRepository studentRepository;
    private final BusRouteRepository routeRepository;
    private final BusPassRepository passRepository;

    public BusPassApplicationService(
            BusPassApplicationRepository applicationRepository,
            StudentRepository studentRepository,
            BusRouteRepository routeRepository,
            BusPassRepository passRepository) {

        this.applicationRepository = applicationRepository;
        this.studentRepository = studentRepository;
        this.routeRepository = routeRepository;
        this.passRepository = passRepository;
    }

    public BusPassApplication submitApplication(
            ApplicationRequest request) {

        Student student = studentRepository
                .findById(request.getStudentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student with ID "
                                        + request.getStudentId()
                                        + " not found"
                        )
                );

        BusRoute route = routeRepository
                .findById(request.getRouteId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Bus route with ID "
                                        + request.getRouteId()
                                        + " not found"
                        )
                );

        // Business Rule:
        // Student cannot have an active bus pass.
        boolean hasActivePass =
                passRepository
                        .existsByApplicationStudentIdAndValidUntilGreaterThanEqual(
                                student.getId(),
                                LocalDate.now()
                        );

        if (hasActivePass) {

            throw new BusinessRuleException(
                    "Student already has an active bus pass"
            );
        }

        BusPassApplication application =
                new BusPassApplication();

        application.setStudent(student);
        application.setRoute(route);
        application.setBoardingPoint(
                request.getBoardingPoint()
        );
        application.setPhotoReference(
                request.getPhotoReference()
        );
        application.setRegistrationReason(
                request.getRegistrationReason()
        );
        application.setStatus(ApplicationStatus.PENDING);

        return applicationRepository.save(application);
    }

    public List<BusPassApplication> getAllApplications() {
        return applicationRepository.findAll();
    }

    public BusPassApplication getApplicationById(Long id) {

        return applicationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Application with ID "
                                        + id
                                        + " not found"
                        )
                );
    }

    public List<BusPassApplication> getApplicationsByStudent(
            Long studentId) {

        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException(
                    "Student with ID " + studentId + " not found"
            );
        }

        return applicationRepository.findByStudentId(studentId);
    }
}