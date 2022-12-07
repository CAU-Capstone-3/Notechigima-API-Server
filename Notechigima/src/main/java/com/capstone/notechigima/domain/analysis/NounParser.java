package com.capstone.notechigima.domain.analysis;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class NounParser {
    public static final String POS_NOUN = "NNP";

    private final Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);

    public Set<String> getNouns(String document) {

        KomoranResult analyzeResult = komoran.analyze(document);
        List<String> nouns = analyzeResult.getTokenList()
                .stream()
                .filter(token -> token.getPos().equals(POS_NOUN))
                .map(Token::getMorph).toList();

        return new HashSet<>(nouns);
    }

}
