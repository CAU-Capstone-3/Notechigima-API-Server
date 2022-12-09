package com.capstone.notechigima.dto.advice;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MergedNoteGetResponseDTO {

    List<AdviceGetDTO> sentences;
}
