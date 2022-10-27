package com.capstone.notechigima.model.dto.section;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class SectionResponseDTO {
    private int sectionId;
    private String title;
    private Date updatedAt;
    private boolean analyzed;
}
