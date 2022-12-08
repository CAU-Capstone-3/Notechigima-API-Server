package com.capstone.notechigima.domain.comment;

import com.capstone.notechigima.domain.BaseTimeEntity;
import com.capstone.notechigima.domain.VisibilityStatus;
import com.capstone.notechigima.domain.advice.Advice;
import com.capstone.notechigima.domain.users.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "advice_comment")
public class Comment extends BaseTimeEntity {

    @Id
    @Column(name = "COMMENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;

    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VisibilityStatus status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ADVICE_ID")
    private Advice advice;

    @Builder
    public Comment(int commentId, String content, VisibilityStatus status, User user, Advice advice) {
        this.commentId = commentId;
        this.content = content;
        this.status = status;
        this.user = user;
        this.advice = advice;
    }
}
