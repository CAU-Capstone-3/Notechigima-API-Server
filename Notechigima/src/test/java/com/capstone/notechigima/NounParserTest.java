package com.capstone.notechigima;

import com.capstone.notechigima.domain.analysis.NounParser;
import kr.bydelta.koala.data.Sentence;
import kr.bydelta.koala.kkma.Tagger;
import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class NounParserTest {

    @Test
    public void parseTest() {
        NounParser nounParser = new NounParser();
        String document = "1.1 Sequence-to-Sequence(seq2seq)은 입력된 시퀀스로부터 다른 도메인의 시퀀스를 출력하는 다양한 분야에서 사용되는 모델입니다. \n\n\n예를 들어 챗봇과 기계 번역이 그러한 대표적인 예인데, 입력 시퀀스와 출력 시퀀스를 각각 질문과 대답으로 구성하면 챗봇으로 만들 수 있고, 입력 시퀀스와 출력 시퀀스를 각각 입력 문";

        Set<String> nonces = nounParser.getNouns(document);
        Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
        KomoranResult result = komoran.analyze(document);

        System.out.println(document);
        int cnt = 0;
        for (Token token : result.getTokenList()) {
            String out = " [" + token.getMorph() + " : " + token.getPos() + "]\t";
            if (++cnt == 5) {
                out = out + "\n";
                cnt = 0;
            }
            System.out.print(out);
        }

        System.out.println();
        System.out.println();
        System.out.println(result.getTokenList());

        for (String nonce : nonces)
            System.out.println(nonce);

        assertThat(nonces).containsOnly("스프링", "빈", "클래스", "어노테이션", "컴포넌트", "df");
        assertThat(1).isEqualTo(2);
    }

    @Test
    public void splitSentenceTest() {

    }
}
