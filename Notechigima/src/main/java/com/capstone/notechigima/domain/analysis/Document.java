package com.capstone.notechigima.domain.analysis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
public class Document {

    private final int noteId;
    private final List<Paragraph> paragraphs;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Paragraph paragraph : paragraphs) {
            for (String sentence : paragraph.getSentences()) {
                sb.append(sentence)
                        .append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
