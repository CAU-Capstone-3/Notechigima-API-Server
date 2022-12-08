package com.capstone.notechigima.domain.analysis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Stack;

@RequiredArgsConstructor
@Component
public class DocumentParser {

    public Document getDocument(int userId, String document) {
        List<Paragraph> paragraphs = getParagraphs(document);
        Document result = new Document(userId, paragraphs);
        return result;
    }

    private List<Paragraph> getParagraphs(String document) {
        List<String> parsed = List.of(document.split("\n"));

        Stack<Paragraph> clusters = new Stack<>();
        clusters.add(new Paragraph());

        for (String line : parsed) {
            if (line.isEmpty()) {
                if (!clusters.peek().isEmpty()) {
                    clusters.add(new Paragraph());
                }
                continue;
            }

            clusters.peek().add(line);
        }

        return clusters;
    }

}
