package com.example.cleanenergy.controller;

import com.example.cleanenergy.entity.MonthlyInnovation;
import com.example.cleanenergy.entity.InnovationType;
import com.example.cleanenergy.repository.MonthlyInnovationRepository;
import com.example.cleanenergy.repository.InnovationTypeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ChartController {

    private final MonthlyInnovationRepository monthlyRepo;
    private final InnovationTypeRepository typeRepo;

    public ChartController(MonthlyInnovationRepository monthlyRepo, InnovationTypeRepository typeRepo) {
        this.monthlyRepo = monthlyRepo;
        this.typeRepo = typeRepo;
    }

    @PostConstruct
    public void initData() {
        if (monthlyRepo.count() == 0) {
            monthlyRepo.saveAll(List.of(
                new MonthlyInnovation("June", 5),
                new MonthlyInnovation("July", 7),
                new MonthlyInnovation("August", 10),
                new MonthlyInnovation("September", 8),
                new MonthlyInnovation("October", 12),
                new MonthlyInnovation("November", 15)
            ));
        }
        if (typeRepo.count() == 0) {
            typeRepo.saveAll(List.of(
                new InnovationType("Solar", 20),
                new InnovationType("Wind", 15),
                new InnovationType("Battery", 10),
                new InnovationType("Hydrogen", 5)
            ));
        }
    }

    @GetMapping("/summary-chart")
    public List<Map<String, Object>> getSummaryChart() {
        return monthlyRepo.findAll().stream()
            .map(mi -> {
                Map<String, Object> map = new HashMap<>();
                map.put("month", mi.getMonth());
                map.put("innovations", mi.getInnovations());
                return map;
            })
            .collect(Collectors.toList());
    }

    @GetMapping("/reports-chart")
    public List<Map<String, Object>> getReportsChart() {
        return typeRepo.findAll().stream()
            .map(it -> {
                Map<String, Object> map = new HashMap<>();
                map.put("type", it.getType());
                map.put("count", it.getCount());
                return map;
            })
            .collect(Collectors.toList());
    }

}