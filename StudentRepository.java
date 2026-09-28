package com.buspass.studentbuspass.repository;

import com.buspass.studentbuspass.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByEmail(String email);
}