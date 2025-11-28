package com.example.cleanenergy.repository;

import com.example.cleanenergy.entity.MonthlyInnovation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthlyInnovationRepository extends JpaRepository<MonthlyInnovation, String> {
}