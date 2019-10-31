package org.rest.masterlist.repository;

import org.rest.masterlist.model.Cathedra;
import org.rest.masterlist.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    List<Teacher> findAllByCathedrasOrderByTotalScoreDesc(Cathedra cathedra);



}
