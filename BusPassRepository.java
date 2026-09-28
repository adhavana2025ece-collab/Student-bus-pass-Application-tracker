package com.buspass.studentbuspass.repository;

import com.buspass.studentbuspass.entity.BusPass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BusPassRepository extends JpaRepository<BusPass, Long> {

    boolean existsByApplicationStudentIdAndValidUntilGreaterThanEqual(
            Long studentId,
            LocalDate date
    );

    List<BusPass> findByValidUntilBetween(
            LocalDate startDate,
            LocalDate endDate
    );
}