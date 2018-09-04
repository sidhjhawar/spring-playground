package com.example.demo.crud;

import com.example.demo.model.Trailer;
import org.springframework.data.repository.CrudRepository;

public interface ITrailerRepository extends CrudRepository<Trailer, Long> {
    Trailer findByTrailerId(Long id);
}
