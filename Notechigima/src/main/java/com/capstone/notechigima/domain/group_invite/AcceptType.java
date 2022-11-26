package com.capstone.notechigima.domain.group_invite;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AcceptType {

    UNCHECKED("UNCHECKED", "요청 미확인 상태"),
    ACCEPTED("ACCEPTED", "초대 수락"),
    DECLINED("DECLINE", "초대 거절");

    private final String key;
    private final String title;
}
