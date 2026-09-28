package com.buspass.studentbuspass.controller;

import com.buspass.studentbuspass.entity.BusRoute;
import com.buspass.studentbuspass.service.BusRouteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
@CrossOrigin(origins = "*")
public class BusRouteController {

    private final BusRouteService routeService;

    public BusRouteController(BusRouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping
    public ResponseEntity<BusRoute> createRoute(
            @RequestBody BusRoute route) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(routeService.createRoute(route));
    }

    @GetMapping
    public ResponseEntity<List<BusRoute>> getAllRoutes() {

        return ResponseEntity.ok(
                routeService.getAllRoutes()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusRoute> getRoute(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                routeService.getRouteById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusRoute> updateRoute(
            @PathVariable Long id,
            @RequestBody BusRoute route) {

        return ResponseEntity.ok(
                routeService.updateRoute(id, route)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoute(
            @PathVariable Long id) {

        routeService.deleteRoute(id);

        return ResponseEntity.ok(
                "Bus route deleted successfully"
        );
    }
}