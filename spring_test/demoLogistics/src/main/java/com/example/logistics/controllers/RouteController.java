package com.example.logistics.controllers;

import com.example.logistics.db.Route;
import com.example.logistics.repositories.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RouteController {

    @Autowired
    RouteRepository routeRepository;

    // Get All Notes
    @GetMapping("/routes")
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    // Create a new Note
    @PostMapping("/routes")
    public Route createRoute(@Valid @RequestBody Route route) {
        return routeRepository.save(route);
    }
    // Get a Single Note

    @GetMapping("/routes/{id}")
    public Route getNoteById(@PathVariable(value = "id") Long routeId) {
        return routeRepository.findById(routeId).get();
    }
    // Update a Note
    @PutMapping("/routes/{id}")
    public Route updateRoute(@PathVariable(value = "id") Long routeId,
                           @Valid @RequestBody Route routeDetails) {

        Route route = routeRepository.findById(routeId).get();

        route.setFrom(routeDetails.getFrom());
        route.setTo(routeDetails.getTo());
        route.setCost(routeDetails.getCost());

        Route updatedRoute = routeRepository.save(route);
        return updatedRoute;
    }
    // Delete a Note
    @DeleteMapping("/routes/{id}")
    public ResponseEntity<?> deleteRoute(@PathVariable(value = "id") Long routeId) {
        Route note = routeRepository.findById(routeId).get();

        routeRepository.deleteById(routeId);

        return ResponseEntity.ok().build();
    }
}