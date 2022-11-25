package com.capstone.notechigima.domain.group_member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GroupAccessType {

    OWNER("OWNER", "그룹장"),
    WRITE("WRITER", "쓰기가 허용된 구성원"),
    READ_ONLY("READ_ONLY", "읽기만 허용된 구성원");

    private final String key;
    private final String title;
}
