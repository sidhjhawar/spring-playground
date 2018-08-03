package com.example.demo.crud;

import com.example.demo.model.Lesson;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface LessonRepository extends CrudRepository<Lesson, Long> {
    Lesson findFirstByTitle(String title);
    List<Lesson> findByDeliveredOnBetween(Date deliveredOn1, Date deliveredOn2);
}
