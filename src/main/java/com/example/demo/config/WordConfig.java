package com.example.demo.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@ConfigurationProperties("wordcount")
@Component
public class WordConfig {

    private boolean caseSensitive;
    private Words words;
    private String pattern;

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    public void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    public Words getWords() {
        return words;
    }

    public void setWords(Words words) {
        this.words = words;
    }

    public static class Words {
        public List<String> skip;

        public List<String> getSkip() {
            return skip;
        }

        public void setSkip(List<String> skip) {
            this.skip = skip;
        }
    }

}
