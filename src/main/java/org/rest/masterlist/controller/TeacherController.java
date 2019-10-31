package org.rest.masterlist.controller;

import lombok.extern.slf4j.Slf4j;
import org.rest.masterlist.exception.EntityNotFoundException;
import org.rest.masterlist.model.Career;
import org.rest.masterlist.model.Cathedra;
import org.rest.masterlist.model.Teacher;
import org.rest.masterlist.repository.CareerRepository;
import org.rest.masterlist.repository.CathedraRepository;
import org.rest.masterlist.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController()
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CathedraRepository cathedraRepository;

    @Autowired
    private CareerRepository careerRepository;

    @GetMapping("teacher/getAllTeachers")
    public List<Teacher> getAllTeachers() {
        log.info("trying to GET all teachers");
        return teacherRepository.findAll();
    }

    @GetMapping("teacher/getTeachersByCathedra")
    public List<Teacher> getTeachersByCathedra(@RequestParam int idCathedra) {
        log.info("trying to GET all teachers by cathedra");
        Optional<Cathedra> cathedra = cathedraRepository.findById(idCathedra);
        if (!cathedra.isPresent()) {
            throw new EntityNotFoundException(idCathedra, "error.message.cathedra.not.found");
        }
        return teacherRepository.findAllByCathedrasOrderByTotalScoreDesc(cathedra.get());
    }

    @GetMapping("teacher/getTeachersByCareer")
    public List<Teacher> getTeachersByCareer(@RequestParam int idCareer) {
        log.info("trying to GET all teachers by career");
        Optional<Career> career = careerRepository.findById(idCareer);
        if (!career.isPresent()) {
            throw new EntityNotFoundException(idCareer, "error.message.career.not.found");
        }
        return null; //TODO hacer una consulta a la BD propia para esto
    }

    @PostMapping("teacher/addTeacher")
    public Teacher addTeacher(@RequestBody Teacher teacher) {
        log.info("trying to ADD a new teacher");
        //TODO existingteacherexception
        return teacherRepository.save(teacher);
    }

}
