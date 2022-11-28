package com.capstone.notechigima.service;

import com.capstone.notechigima.config.ExceptionCode;
import com.capstone.notechigima.config.jwt.JwtProvider;
import com.capstone.notechigima.domain.comment.Comment;
import com.capstone.notechigima.domain.group_invite.GroupInvite;
import com.capstone.notechigima.domain.group_member.GroupAccessType;
import com.capstone.notechigima.domain.group_member.GroupMember;
import com.capstone.notechigima.domain.note.Note;
import com.capstone.notechigima.domain.sentence.Sentence;
import com.capstone.notechigima.domain.sentence_advice.Advice;
import com.capstone.notechigima.domain.study_group.StudyGroup;
import com.capstone.notechigima.domain.subject.Subject;
import com.capstone.notechigima.domain.topic.Topic;
import com.capstone.notechigima.domain.users.User;
import com.capstone.notechigima.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.AccessException;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

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

    public void authorizationByUserId(String token, int userId) throws AccessException, NotFoundException {
        if (getUserId(token) != userId) {
            throw createAccessException();
        }
    }

    public void authorizationByGroupId(String token, int groupId) throws AccessException, NotFoundException {
        int userId = getUserId(token);
        List<GroupMember> groupMembers = groupMemberRepository.findAllByUser_UserId(userId);
        for (GroupMember member : groupMembers) {
            if (member.getStudyGroup().getGroupId() == groupId) return;
        }
        throw createAccessException();
    }

    private void authorizationByGroup(String token, StudyGroup group) throws AccessException, NotFoundException {
        int userId = getUserId(token);
        List<GroupMember> groupMembers = groupMemberRepository.findAllByUser_UserId(userId);
        for (GroupMember member : groupMembers) {
            if (member.getStudyGroup() == group) return;
        }
        throw createAccessException();
    }

    private void authorizationBySubject(String token, Subject subject) throws AccessException, NotFoundException {
        authorizationByGroup(token, subject.getStudyGroup());
    }

    public void authorizationBySubjectId(String token, int subjectId) throws AccessException, NotFoundException {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(this::createNotFoundException);
        authorizationBySubject(token, subject);
    }


    private void authorizationByTopic(String token, Topic topic) throws AccessException, NotFoundException {
        authorizationBySubject(token, topic.getSubject());
    }

    public void authorizationByTopicId(String token, int topicId) throws AccessException, NotFoundException {
        Topic topic = topicRepository.findById(topicId).orElseThrow(this::createNotFoundException);
        authorizationByTopic(token, topic);
    }

    private void authorizationByNote(String token, Note note) throws AccessException, NotFoundException {
        authorizationByTopic(token, note.getTopic());
    }

    public void authorizationByNoteId(String token, int noteId) throws AccessException, NotFoundException {
        Note note = noteRepository.findById(noteId).orElseThrow(this::createNotFoundException);
        authorizationByNote(token, note);
    }

    private void authorizationBySentence(String token, Sentence sentence) throws AccessException, NotFoundException {
        authorizationByNote(token, sentence.getNote());
    }

    public void authorizationBySentenceId(String token, int sentenceId) throws AccessException, NotFoundException {
        Sentence sentence = sentenceRepository.findById(sentenceId).orElseThrow(this::createNotFoundException);
        authorizationBySentence(token, sentence);
    }

    private void authorizationByAdvice(String token, Advice advice) throws AccessException, NotFoundException {
        authorizationBySentence(token, advice.getSentence1());
    }

    public void authorizationByAdviceId(String token, int adviceId) throws AccessException, NotFoundException {
        Advice advice = adviceRepository.findById(adviceId).orElseThrow(this::createNotFoundException);
        authorizationByAdvice(token, advice);
    }

    private void authorizationByComment(String token, Comment comment) throws AccessException, NotFoundException {
        authorizationByAdvice(token, comment.getAdvice());
    }

    public void authorizationByCommentId(String token, int commentId) throws AccessException, NotFoundException {
        Comment comment = commentRepository.findById(commentId).orElseThrow(this::createNotFoundException);
        authorizationByComment(token, comment);
    }

    public void authorizationByGroupOwner(String token, int groupId) throws AccessException, NotFoundException {
        int userId = getUserId(token);
        List<GroupMember> members = groupMemberRepository.findAllByStudyGroup_GroupId(groupId);
        for (GroupMember member : members) {
            if (member.getUser().getUserId() == userId &&
                    member.getAccess() == GroupAccessType.OWNER) return;
        }
        throw createAccessException();
    }

    private void authorizationByInvite(String token, GroupInvite invite) throws AccessException, NotFoundException {
        authorizationByGroup(token, invite.getStudyGroup());
    }

    public void authorizationByInviteId(String token, int inviteId) throws AccessException, NotFoundException {
        GroupInvite invite = groupInviteRepository.findById(inviteId).orElseThrow(this::createNotFoundException);
        authorizationByInvite(token, invite);
    }

    private AccessException createAccessException() {
        return new AccessException(ExceptionCode.PERMISSION_DENIED.getMessage());
    }

    private NotFoundException createNotFoundException() {
        return new NotFoundException(ExceptionCode.ERROR_NOT_FOUND_RESOURCE.getMessage());
    }

    private int getUserId(String token) throws NotFoundException, AccessException {
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
