package com.capstone.notechigima.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum VisibilityStatus {

    VISIBLE("VISIBLE", "열람 가능한 상태"),
    INVISIBLE("INVISIBLE", "삭제되어 열람이 불가능한 상태");

    private final String key;
    private final String title;
}
