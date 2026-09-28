package com.buspass.studentbuspass.service;

import com.buspass.studentbuspass.entity.BusRoute;
import com.buspass.studentbuspass.exception.ResourceNotFoundException;
import com.buspass.studentbuspass.repository.BusRouteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusRouteService {

    private final BusRouteRepository routeRepository;

    public BusRouteService(BusRouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public BusRoute createRoute(BusRoute route) {
        return routeRepository.save(route);
    }

    public List<BusRoute> getAllRoutes() {
        return routeRepository.findAll();
    }

    public BusRoute getRouteById(Long id) {

        return routeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Bus route with ID " + id + " not found"
                        )
                );
    }

    public BusRoute updateRoute(Long id, BusRoute updatedRoute) {

        BusRoute route = getRouteById(id);

        route.setRouteName(updatedRoute.getRouteName());
        route.setSource(updatedRoute.getSource());
        route.setDestination(updatedRoute.getDestination());
        route.setBoardingPoint(updatedRoute.getBoardingPoint());

        return routeRepository.save(route);
    }

    public void deleteRoute(Long id) {

        BusRoute route = getRouteById(id);

        routeRepository.delete(route);
    }
}