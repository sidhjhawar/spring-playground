package com.example.demo.controller;


import com.example.demo.crud.LessonRepository;
import com.example.demo.model.Lesson;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/lessons")
public class LessonsController {

    private final LessonRepository repository;

    public LessonsController(LessonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public Iterable<Lesson> all() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Lesson> lesson(@PathVariable Long id) {
        return this.repository.findById(id);
    }

    @PostMapping("")
    public Lesson create(@RequestBody Lesson lesson) {
        return this.repository.save(lesson);
    }

    @DeleteMapping("/{id}")
    public String deleteLesson(@PathVariable Long id) {
        this.repository.deleteById(id);
        return "Hello";
    }

    @PatchMapping(value = "/{id}")
    public String patchLesson(@PathVariable Long id, @RequestBody Lesson updatedLesson) throws JsonProcessingException {
        Lesson lesson = this.repository.findById(id).get();
        lesson.setDeliveredOn(updatedLesson.getDeliveredOn());
        lesson.setTitle(updatedLesson.getTitle());
        this.repository.save(lesson);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(lesson);

    }

    @GetMapping("/find/{title}")
    public Lesson findLessonByTitle(@PathVariable String title) {
        return this.repository.findFirstByTitle(title);
    }

    @GetMapping("/between")
    public List<Lesson> findLessonBetweenDates(@RequestParam String date1, @RequestParam String date2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return this.repository.findByDeliveredOnBetween(sdf.parse(date1),sdf.parse(date2));
    }

}