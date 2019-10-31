package org.rest.masterlist.repository;

import org.rest.masterlist.model.Comment;
import org.rest.masterlist.model.Teacher;
import org.rest.masterlist.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findByTeacherOrderByDateTimeDesc(Teacher teacher);
    List<Comment> findAllByUserInfoOrderByDateTimeDesc(UserInfo user);



}
