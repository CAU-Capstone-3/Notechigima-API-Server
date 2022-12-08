package com.capstone.notechigima.domain.analysis;

import kr.bydelta.koala.data.Sentence;
import kr.bydelta.koala.kmr.Tagger;
import kr.bydelta.koala.proc.SentenceSplitter;
import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class Paragraph {

    private static Tagger tagger;
    private static Komoran komoran;

    private final int noteId;
    private final List<String> sentences = new ArrayList<>();
    private final Set<String> nouns = new HashSet<>();

    public void add(String paragraph) {
        sentences.addAll(getParsedSentence(paragraph));
    }

    public boolean isEmpty() {
        return sentences.isEmpty();
    }

    public List<String> getSentences() {
        return Collections.unmodifiableList(sentences);
    }

    public List<String> getNouns() {
        return List.copyOf(nouns);
    }

    public int countSameNouns(List<String> nouns) {
        int result = 0;
        for (String noun : nouns) {
            if (this.nouns.contains(noun)) {
                result++;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String sentence : sentences) {
            sb.append(sentence).append(" ");
        }
        return sb.toString();
    }

    public int getNoteId() {
        return this.noteId;
    }

    private List<String> getParsedSentence(String input) {
        addNouns(input);
        Sentence taggedSentence = getTagger().tagSentence(input);
        List<Sentence> paragraph = SentenceSplitter.sentences(taggedSentence);

        return paragraph
                .stream()
                .map(Sentence::surfaceString)
                .collect(Collectors.toList());
    }

    private void addNouns(String input) {
        List<String> newNouns = getKomoran().analyze(input)
                .getNouns()
                .stream()
                .filter(noun -> noun.length() > 1)
                .toList();

        nouns.addAll(newNouns);
    }

    private Tagger getTagger() {
        if (tagger == null) {
            tagger = new Tagger();
        }
        return tagger;
    }

    private Komoran getKomoran() {
        if (komoran == null) {
            komoran = new Komoran(DEFAULT_MODEL.FULL);
        }
        return komoran;
    }
}
