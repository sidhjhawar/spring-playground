package com.example.demo.controller;

import com.example.demo.service.WordCounter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/words/")
public class WordController {
    private WordCounter wordCounter;

    public WordController(WordCounter wordCounter) {
        this.wordCounter = wordCounter;
    }

    @PostMapping("/count")
    public String count(@RequestBody String input) throws JsonProcessingException {
        ObjectMapper objectMapper =  new ObjectMapper();
        return objectMapper.writeValueAsString(wordCounter.count(input));
    }
}
