package com.capstone.notechigima.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ActiveStatus {

    ACTIVE("ACTIVE", "이용 가능한 상태"),
    INACTIVE("INACTIVE", "이용 불가능한 상태");

    private final String key;
    private final String title;
}
