package com.example.logistics.repositories;

import com.example.logistics.db.Route;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Repository
public interface RouteRepository extends JpaRepository<Route,Long> {
}
