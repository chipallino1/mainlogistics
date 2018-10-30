package com.example.logistics.repositories;

import com.example.logistics.db.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repositories extends JpaRepository<Route,Long> {
}
