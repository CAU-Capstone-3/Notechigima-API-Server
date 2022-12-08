package com.capstone.notechigima;

import com.capstone.notechigima.domain.analysis.Document;
import com.capstone.notechigima.domain.analysis.DocumentParser;
import com.capstone.notechigima.domain.analysis.Paragraph;
import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import org.junit.jupiter.api.Test;

public class DocumentParseTest {

    @Test
    public void documentParsingTest() {
        String input = "1.1 Sequence-to-Sequence(seq2seq)은 입력된 시퀀스로부터 다른 도메인의 시퀀스를 출력하는 다양한 분야에서 사용되는 모델입니다. \n\n\n예를 들어 챗봇과 기계 번역이 그러한 대표적인 예인데, 입력 시퀀스와 출력 시퀀스를 각각 질문과 대답으로 구성하면 챗봇으로 만들 수 있고, 입력 시퀀스와 출력 시퀀스를 각각 입력 문장을 생성해요. 나는 언제 잠을 잘 수 있을까요.\n슬슬졸린데 큰일이다.";
        DocumentParser parser = new DocumentParser();
        Document document = parser.getDocument(1, input);


        Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
        KomoranResult result = komoran.analyze("1.1 Sequence-to-Sequence(seq2seq)은 입력된 시퀀스로부터 다른 도메인의 시퀀스를 출력하는 다양한 분야에서 사용되는 모델입니다. \n\n\n예를 들어 챗봇과 기계 번역이 그러한 대표적인 예인데, 입력 시퀀스와 출력 시퀀스를 각각 질문과 대답으로 구성하면 챗봇으로 만들 수 있고, 입력 시퀀스와 출력 시퀀스를 각각 입력 문장을 생성해요. 나는 언제 잠을 잘 수 있을까요.\n슬슬졸린데 큰일이다.");
        System.out.println(result.getNouns());

        Paragraph paragraph = new Paragraph();
        paragraph.add("1.1 Sequence-to-Sequence(seq2seq)은 입력된 시퀀스로부터 다른 도메인의 시퀀스를 출력하는 다양한 분야에서 사용되는 모델입니다. \n\n\n예를 들어 챗봇과 기계 번역이 그러한 대표적인 예인데, 입력 시퀀스와 출력 시퀀스를 각각 질문과 대답으로 구성하면 챗봇으로 만들 수 있고, 입력 시퀀스와 출력 시퀀스를 각각 입력 문장을 생성해요. 나는 언제 잠을 잘 수 있을까요.\n슬슬졸린데 큰일이다.");
        paragraph.add("1, 2 계층의 역할\n점대점 연결된 노드 사이의 프레임을 교환\n점대점 연결에서의 통신의 실체는 다음과 같다.\n1. 비트 교환 : 신호 인코딩, 디지털 전송\n2. 비트 묶음 교환 : 프레이밍\n3. 오류 검출 및 복구\n노드는 하드웨어적 구성요소이다.\n노드는 단말(호스트) 스위치, 라우터를 모두 포함한다.\n스위치 : 전송받은 패킷을 다시 다음 노드로 전달 해주는 역할 -> 2계층\n라우터 : 네트워크를 다른 네트워크로 연결한다. -> 3계층\nStore & Forward를 위해서 메모리가 사용된다.\n유한 메모리로 인해 버퍼공간은 제한이 있다.\n프로세서나 NIC는 빠르기 때문에 메모리가 병목 구간이 된다.\n네트워크 어댑터는 하드웨어적 구현을 통해 모뎀, 오류 검출, 복구 등을 지원한다.\n링크는 논리적 통로이며, 하나의 케이블에 여러 링크를 형성할 수 있음\n기지국과 단말기 사이의 통신\n셀은 하나의 기지국이 관할하는 지역\n셀을 옮겨갈 때 핸드오프 문제가 발생\n원형 셀의 겹치는 구간은 양쪽 기지국이 동시에 담당\n주파수를 재사용할 수 있기 떄문에 기지국이 멀리 있는 것이 좋을 때도 있음\n기지국이 감당할 수 있는 대역폭에 한계가 있음\n마이크로셀은 인구 밀집 지역에서 대역폭 한계를 극복");
        System.out.println(paragraph.getSentences());
        System.out.println(paragraph.getNouns());

        result = komoran.analyze("1, 2 계층의 역할\n점대점 연결된 노드 사이의 프레임을 교환\n점대점 연결에서의 통신의 실체는 다음과 같다.\n1. 비트 교환 : 신호 인코딩, 디지털 전송\n2. 비트 묶음 교환 : 프레이밍\n3. 오류 검출 및 복구\n노드는 하드웨어적 구성요소이다.\n노드는 단말(호스트) 스위치, 라우터를 모두 포함한다.\n스위치 : 전송받은 패킷을 다시 다음 노드로 전달 해주는 역할 -> 2계층\n라우터 : 네트워크를 다른 네트워크로 연결한다. -> 3계층\nStore & Forward를 위해서 메모리가 사용된다.\n유한 메모리로 인해 버퍼공간은 제한이 있다.\n프로세서나 NIC는 빠르기 때문에 메모리가 병목 구간이 된다.\n네트워크 어댑터는 하드웨어적 구현을 통해 모뎀, 오류 검출, 복구 등을 지원한다.\n링크는 논리적 통로이며, 하나의 케이블에 여러 링크를 형성할 수 있음\n기지국과 단말기 사이의 통신\n셀은 하나의 기지국이 관할하는 지역\n셀을 옮겨갈 때 핸드오프 문제가 발생\n원형 셀의 겹치는 구간은 양쪽 기지국이 동시에 담당\n주파수를 재사용할 수 있기 떄문에 기지국이 멀리 있는 것이 좋을 때도 있음\n기지국이 감당할 수 있는 대역폭에 한계가 있음\n마이크로셀은 인구 밀집 지역에서 대역폭 한계를 극복");
        System.out.println(result.getTokenList());

        System.out.println(document.toString());
    }
}
