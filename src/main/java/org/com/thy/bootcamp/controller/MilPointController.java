package org.com.thy.bootcamp.controller;

import lombok.RequiredArgsConstructor;
import org.com.thy.bootcamp.entity.MilPoint;
import org.com.thy.bootcamp.service.MilPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/milpoints")
public class MilPointController {


    private final MilPointService milPointService;

    @PostMapping
    public ResponseEntity<MilPoint> createMilPoint(@RequestBody MilPoint milPoint) {
        MilPoint createdMilPoint = milPointService.createMilPoint(milPoint);
        return ResponseEntity.ok(createdMilPoint);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MilPoint> getMilPoint(@PathVariable Long id) {
        MilPoint milPoint = milPointService.getMilPoint(id);
        return ResponseEntity.ok(milPoint);
    }

    @GetMapping
    public ResponseEntity<List<MilPoint>> getAllMilPoints() {
        List<MilPoint> milPoints = milPointService.getAllMilPoints();
        return ResponseEntity.ok(milPoints);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MilPoint> updateMilPoint(@PathVariable Long id, @RequestParam BigDecimal amount) {
        MilPoint updatedMilPoint = milPointService.updateMilPoint(id, amount);
        return ResponseEntity.ok(updatedMilPoint);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMilPoint(@PathVariable Long id) {
        milPointService.deleteMilPoint(id);
        return ResponseEntity.noContent().build();
    }
}
