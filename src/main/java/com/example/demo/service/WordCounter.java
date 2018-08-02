package com.example.demo.service;


import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WordCounter {

    public Map<String, Integer> count(String input) {
        String pattern = "[\\p{Punct}\\s]+";
        Map<String, Integer> wordCountMap =  new HashMap<>();
        String[] splitString = input.split(pattern);
        for(String s: splitString) {
            if(wordCountMap.containsKey(s)){
                int value = wordCountMap.get(s).intValue();
               wordCountMap.put(s, ++value);
            } else {
                wordCountMap.put(s, 1);
            }
        }
        return wordCountMap;
    }
}
