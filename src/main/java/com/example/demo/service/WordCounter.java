package com.example.demo.service;

import com.example.demo.config.WordConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class WordCounter {

    @Autowired
    private WordConfig wordConfig;

    public Map<String, Integer> count(String input) {
        String pattern = "[\\p{Punct}\\s]+";
        Map<String, Integer> wordCountMap = new HashMap<>();
        String[] splitString = input.split(pattern);
        for (String s : splitString) {
            String lowerCaseString = s;
            if (!wordConfig.isCaseSensitive()) {
                lowerCaseString = s.toLowerCase();
            }
            if (!wordConfig.words.getSkip().contains(lowerCaseString)) {
                if (wordCountMap.containsKey(lowerCaseString)) {
                    int value = wordCountMap.get(lowerCaseString);
                    wordCountMap.put(lowerCaseString, ++value);
                } else {
                    wordCountMap.put(lowerCaseString, 1);
                }
            }
        }
        return wordCountMap;
    }

}
