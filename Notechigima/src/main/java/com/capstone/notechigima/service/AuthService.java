package com.capstone.notechigima.service;

import com.capstone.notechigima.config.ExceptionCode;
import com.capstone.notechigima.config.jwt.JwtProvider;
import com.capstone.notechigima.domain.comment.Comment;
import com.capstone.notechigima.domain.group_member.GroupMember;
import com.capstone.notechigima.domain.note.Note;
import com.capstone.notechigima.domain.sentence.Sentence;
import com.capstone.notechigima.domain.sentence_advice.Advice;
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

    public void validateByUserId(String token, int userId) throws AccessException, NotFoundException {
        if (getUserId(token) != userId) {
            throw createAccessException();
        }
    }

    public void validateByGroupId(String token, int groupId) throws AccessException, NotFoundException {
        int userId = getUserId(token);
        List<GroupMember> groupMembers = groupMemberRepository.findAllByUser_UserId(userId);
        for (GroupMember member : groupMembers) {
            if (member.getStudyGroup().getGroupId() == groupId) return;
        }
        throw createAccessException();
    }

    public void validateBySubjectId(String token, int subjectId) throws AccessException, NotFoundException {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(this::createNotFoundException);
        validateByGroupId(token, subject.getStudyGroup().getGroupId());
    }

    public void validateByTopicId(String token, int topicId) throws AccessException, NotFoundException {
        Topic topic = topicRepository.findById(topicId).orElseThrow(this::createNotFoundException);
        validateBySubjectId(token, topic.getSubject().getSubjectId());
    }

    public void validateByNoteId(String token, int noteId) throws AccessException, NotFoundException {
        Note note = noteRepository.findById(noteId).orElseThrow(this::createNotFoundException);
        validateByTopicId(token, note.getTopic().getTopicId());
    }

    public void validateBySentenceId(String token, int sentenceId) throws AccessException, NotFoundException {
        Sentence sentence = sentenceRepository.findById(sentenceId).orElseThrow(this::createNotFoundException);
        validateByNoteId(token, sentence.getNote().getNoteId());
    }

    public void validateByAdviceId(String token, int adviceId) throws AccessException, NotFoundException {
        Advice advice = adviceRepository.findById(adviceId).orElseThrow(this::createNotFoundException);
        validateBySentenceId(token, advice.getSentence1().getSentenceId());
    }

    public void validateByCommentId(String token, int commentId) throws AccessException, NotFoundException {
        Comment comment = commentRepository.findById(commentId).orElseThrow(this::createNotFoundException);
        validateByAdviceId(token, comment.getAdvice().getAdviceId());
    }
    
    private AccessException createAccessException() {
        return new AccessException(ExceptionCode.PERMISSION_DENIED.getMessage());
    }

    private NotFoundException createNotFoundException() {
        return new NotFoundException(ExceptionCode.ERROR_NOT_FOUND_RESOURCE.getMessage());
    }

    private int getUserId(String token) throws NotFoundException {
        token = parseToken(token);
        String email = jwtProvider.getEmailFromToken(token);
        User user = userRepository.getUserByEmail(email).orElseThrow(
                this::createNotFoundException);
        return user.getUserId();
    }

}
