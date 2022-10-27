package com.capstone.notechigima.model.dao.section;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class SectionEntity {
    private int sectionId;
    private int subjectId;
    private String title;
    private Date updatedAt;
    private boolean analyzed;
}
