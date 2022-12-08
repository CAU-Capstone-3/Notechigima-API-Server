package com.capstone.notechigima.domain.analysis;

import lombok.*;

@Getter
@AllArgsConstructor
@Builder
public class Contribution {
    private final int userId;
    private final String userName;
    private final double contribution;
}
