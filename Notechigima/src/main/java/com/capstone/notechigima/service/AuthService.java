package com.capstone.notechigima.service;

import com.capstone.notechigima.config.ExceptionCode;
import com.capstone.notechigima.config.RestApiException;
import com.capstone.notechigima.config.jwt.JwtProvider;
import com.capstone.notechigima.domain.comment.Comment;
import com.capstone.notechigima.domain.group_invite.GroupInvite;
import com.capstone.notechigima.domain.group_member.GroupAccessType;
import com.capstone.notechigima.domain.group_member.GroupMember;
import com.capstone.notechigima.domain.note.Note;
import com.capstone.notechigima.domain.sentence.Sentence;
import com.capstone.notechigima.domain.advice.Advice;
import com.capstone.notechigima.domain.study_group.StudyGroup;
import com.capstone.notechigima.domain.subject.Subject;
import com.capstone.notechigima.domain.topic.Topic;
import com.capstone.notechigima.domain.users.User;
import com.capstone.notechigima.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.capstone.notechigima.config.jwt.JwtUtils.parseToken;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final SubjectRepository subjectRepository;
    private final TopicRepository topicRepository;
    private final NoteRepository noteRepository;
    private final SentenceRepository sentenceRepository;
    private final AdviceRepository adviceRepository;
    private final CommentRepository commentRepository;
    private final GroupInviteRepository groupInviteRepository;

    public void authorizationByUserId(String token, int userId) throws RestApiException {
        if (getUserId(token) != userId) {
            throw createAccessException();
        }
    }

    public void authorizationByGroupId(String token, int groupId) throws RestApiException {
        int userId = getUserId(token);
        List<GroupMember> groupMembers = groupMemberRepository.findAllByUser_UserId(userId);
        for (GroupMember member : groupMembers) {
            if (member.getStudyGroup().getGroupId() == groupId) return;
        }
        throw createAccessException();
    }

    private void authorizationByGroup(String token, StudyGroup group) throws RestApiException {
        int userId = getUserId(token);
        List<GroupMember> groupMembers = groupMemberRepository.findAllByUser_UserId(userId);
        for (GroupMember member : groupMembers) {
            if (member.getStudyGroup() == group) return;
        }
        throw createAccessException();
    }

    private void authorizationBySubject(String token, Subject subject) throws RestApiException {
        authorizationByGroup(token, subject.getStudyGroup());
    }

    public void authorizationBySubjectId(String token, int subjectId) throws RestApiException {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(this::createNotFoundException);
        authorizationBySubject(token, subject);
    }


    private void authorizationByTopic(String token, Topic topic) throws RestApiException {
        authorizationBySubject(token, topic.getSubject());
    }

    public void authorizationByTopicId(String token, int topicId) throws RestApiException {
        Topic topic = topicRepository.findById(topicId).orElseThrow(this::createNotFoundException);
        authorizationByTopic(token, topic);
    }

    private void authorizationByNote(String token, Note note) throws RestApiException {
        authorizationByTopic(token, note.getTopic());
    }

    public void authorizationByNoteId(String token, int noteId) throws RestApiException {
        Note note = noteRepository.findById(noteId).orElseThrow(this::createNotFoundException);
        authorizationByNote(token, note);
    }

    private void authorizationByAdvice(String token, Advice advice) throws RestApiException {
        authorizationByTopicId(token, advice.getTopic().getTopicId());
    }

    public void authorizationByAdviceId(String token, int adviceId) throws RestApiException {
        Advice advice = adviceRepository.findById(adviceId).orElseThrow(this::createNotFoundException);
        authorizationByAdvice(token, advice);
    }

    private void authorizationByComment(String token, Comment comment) throws RestApiException {
        authorizationByAdvice(token, comment.getAdvice());
    }

    public void authorizationByCommentId(String token, int commentId) throws RestApiException {
        Comment comment = commentRepository.findById(commentId).orElseThrow(this::createNotFoundException);
        authorizationByComment(token, comment);
    }

    public void authorizationByGroupOwner(String token, int groupId) throws RestApiException {
        int userId = getUserId(token);
        List<GroupMember> members = groupMemberRepository.findAllByStudyGroup_GroupId(groupId);
        for (GroupMember member : members) {
            if (member.getUser().getUserId() == userId &&
                    member.getAccess() == GroupAccessType.OWNER) return;
        }
        throw createAccessException();
    }

    private void authorizationByInvite(String token, GroupInvite invite) throws RestApiException {
        if (invite.getUser().getUserId() != getUserId(token)) {
            throw createAccessException();
        }
    }

    public void authorizationByInviteId(String token, int inviteId) throws RestApiException {
        GroupInvite invite = groupInviteRepository.findById(inviteId).orElseThrow(this::createNotFoundException);
        authorizationByInvite(token, invite);
    }

    private RestApiException createAccessException() {
        return new RestApiException(ExceptionCode.PERMISSION_DENIED);
    }

    private RestApiException createNotFoundException() {
        return new RestApiException(ExceptionCode.ERROR_NOT_FOUND_RESOURCE);
    }

    private int getUserId(String token) throws RestApiException {
        try {
            token = parseToken(token);
        } catch (SecurityException e) {
            throw createAccessException();
        }

        String email = jwtProvider.getEmailFromToken(token);
        User user = userRepository.getUserByEmail(email).orElseThrow(
                this::createNotFoundException);
        return user.getUserId();
    }

}
