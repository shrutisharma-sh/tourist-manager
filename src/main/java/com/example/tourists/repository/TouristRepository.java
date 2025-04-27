package com.example.tourists.repository;

import com.example.tourists.model.Tourist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TouristRepository  extends JpaRepository<Tourist, Integer> {
}
