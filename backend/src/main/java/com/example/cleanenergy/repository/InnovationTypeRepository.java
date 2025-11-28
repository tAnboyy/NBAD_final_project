package com.example.cleanenergy.repository;

import com.example.cleanenergy.entity.InnovationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InnovationTypeRepository extends JpaRepository<InnovationType, String> {
}