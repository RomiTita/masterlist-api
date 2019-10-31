package org.rest.masterlist.handler;

import org.rest.masterlist.exception.EntityNotFoundException;
import org.rest.masterlist.model.Comment;
import org.rest.masterlist.model.Teacher;
import org.rest.masterlist.model.UserInfo;
import org.rest.masterlist.repository.CommentRepository;
import org.rest.masterlist.repository.TeacherRepository;
import org.rest.masterlist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class CommentHandler {

    @Autowired
    private CommentRepository repository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(rollbackOn = Exception.class)
    public Comment addCommentTransaction(Comment comment) {

        Optional<Teacher> teacher = teacherRepository.findById(comment.getTeacher().getIdTeacher());
        if (!teacher.isPresent()) {
            throw new EntityNotFoundException(comment.getTeacher().getIdTeacher(), "error.message.teacher.not.found");
        }
        comment.setTeacher(teacher.get());

        Optional<UserInfo> user = userRepository.findById(comment.getUserInfo().getIdUser());
        if (!user.isPresent()) {
            throw new EntityNotFoundException(comment.getUserInfo().getIdUser(), "error.message.user.not.found");
        }
        comment.setUserInfo(user.get());

        double score = getAverage(repository.findByTeacherOrderByDateTimeDesc(comment.getTeacher()), comment.getScore());
        comment.getTeacher().setTotalScore(score);
        teacherRepository.save(comment.getTeacher());

        comment.setDateTime(LocalDateTime.now());

        return repository.save(comment);
    }

    private double getAverage(List<Comment> allComments, double newScore) {
        int count = allComments.size() + 1;
        double total = newScore;

        for (Comment c : allComments) {
            total += c.getScore();
        }

        return (count != 0) ? total / count : 0;
    }
}
