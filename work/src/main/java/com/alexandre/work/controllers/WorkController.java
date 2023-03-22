package com.alexandre.work.controllers;

import com.alexandre.work.entity.Work;
import com.alexandre.work.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/av1/work")
public class WorkController {

    @Autowired
    private WorkService workService;

    @GetMapping
    public ResponseEntity<List<Work>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(workService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Work>> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(workService.findByid(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        workService.deleteById(id);
    }

}
