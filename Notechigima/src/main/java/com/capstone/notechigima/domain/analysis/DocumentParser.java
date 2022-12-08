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
//            if (line.isEmpty()) {
//                if (!paragraphs.peek().isEmpty()) {
//                    paragraphs.add(new Paragraph(noteId));
//                }
//                continue;
//            }
            if (!paragraphs.peek().isEmpty())
                paragraphs.add(new Paragraph(noteId));
            paragraphs.peek().add(line);
        }

        return paragraphs;
    }

}
