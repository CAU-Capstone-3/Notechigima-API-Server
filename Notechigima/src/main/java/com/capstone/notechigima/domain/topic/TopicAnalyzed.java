package com.capstone.notechigima.domain.topic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TopicAnalyzed {

    UNREADY("UNREADY", "토픽 내에 노트를 작성하지 않은 사람이 있어서 분석 불가"),
    READY("READY", "노트를 모두 작성하여 분석 가능"),
    RUNNING("RUNNING", "분석 중"),
    FINISH("FINISH", "분석 완료");

    private final String key;
    private final String title;
}
