package org.rest.masterlist.controller;

import lombok.extern.slf4j.Slf4j;
import org.rest.masterlist.exception.EntityNotFoundException;
import org.rest.masterlist.handler.CommentHandler;
import org.rest.masterlist.model.Comment;
import org.rest.masterlist.model.Teacher;
import org.rest.masterlist.model.UserInfo;
import org.rest.masterlist.repository.CommentRepository;
import org.rest.masterlist.repository.TeacherRepository;
import org.rest.masterlist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class CommentController {

    @Autowired
    private CommentRepository repository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentHandler handler;

    @PostMapping("comment/addComment")
    public String addComment(@RequestBody Comment comment) {
        log.info("trying to ADD a new comment");
        Comment c = handler.addCommentTransaction(comment);
        return (c != null) ? "message.success" : "message.unknown";
    }

    @GetMapping("comment/getAllTeacherComments")
    public List<Comment> getAllTeacherComments(@RequestParam int idTeacher) {
        log.info("trying to GET all teachers comments");
        Optional<Teacher> teacher = teacherRepository.findById(idTeacher);
        if (teacher.isPresent()) {
            return repository.findByTeacherOrderByDateTimeDesc(teacher.get());
        } else {
            throw new EntityNotFoundException(idTeacher, "error.message.teacher.not.found");
        }
    }

    @GetMapping( value = "comment/getCommentsByUser")
    public List<Comment> getCommentsByUser(@RequestParam int idUser) {
        log.info("trying to get getCommentsByUser");
        Optional<UserInfo> userInfo = userRepository.findById(idUser);
        if (userInfo.isPresent()) {
            return repository.findAllByUserInfoOrderByDateTimeDesc(userInfo.get());
        } else {
            throw new EntityNotFoundException(idUser, "error.message.user.not.found");
        }
    }

}
