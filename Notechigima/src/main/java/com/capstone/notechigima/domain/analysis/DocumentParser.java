package com.capstone.notechigima.domain.analysis;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Stack;

@Component
public class DocumentParser {


    public Document parse(int noteId, String document) {
        List<Paragraph> paragraphs = getParagraphs(document, noteId);
        Document result = new Document(noteId, paragraphs);
        return result;
    }

    private List<Paragraph> getParagraphs(String document, int noteId) {
        List<String> parsed = List.of(document.split("\n"));

        Stack<Paragraph> paragraphs = new Stack<>();
        paragraphs.add(new Paragraph(noteId));

        for (String line : parsed) {
            // 개행 문자가 또 들어왔을 때
            if (line.isEmpty()) {
                // 최근에 만든 문단이 비지 않았으면 새로운 문단 생성
                if (!paragraphs.peek().isEmpty()) {
                    paragraphs.add(new Paragraph(noteId));
                }
                continue;
            }

            paragraphs.peek().add(line);
        }

        // 빈 문단 제거
        if (paragraphs.peek().isEmpty())
            paragraphs.pop();

        return paragraphs;
    }

}
