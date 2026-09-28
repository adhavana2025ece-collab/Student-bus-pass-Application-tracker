package com.buspass.studentbuspass.repository;

import com.buspass.studentbuspass.entity.BusPassApplication;
import com.buspass.studentbuspass.entity.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusPassApplicationRepository
        extends JpaRepository<BusPassApplication, Long> {

    List<BusPassApplication> findByStudentId(Long studentId);

    List<BusPassApplication> findByStatus(ApplicationStatus status);
}