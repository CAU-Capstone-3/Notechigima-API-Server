package com.capstone.notechigima.domain.analysis;

import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class SentenceUnit {
    private final String sentence;
    private final Set<String> nouns = new HashSet<>();

    public String getSentence() {
        System.out.println(nouns);
        return this.sentence;
    }

    public void addNoun(String noun) {
        nouns.add(noun);
    }

    public void addNounAll(List<String> nouns) {
        this.nouns.addAll(nouns);
    }
}
