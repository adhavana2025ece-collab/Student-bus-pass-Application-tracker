package com.buspass.studentbuspass.repository;

import com.buspass.studentbuspass.entity.BusRoute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRouteRepository extends JpaRepository<BusRoute, Long> {
}