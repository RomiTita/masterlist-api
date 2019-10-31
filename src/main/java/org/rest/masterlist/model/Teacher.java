package org.rest.masterlist.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
@Entity
public class Teacher {

    private @Id @GeneratedValue int idTeacher;
    private String name;
    private String lastname;
    private double totalScore;

    @ManyToMany(mappedBy = "teachers")
    private List<Cathedra> cathedras;

    public Teacher() {}

    public Teacher(String name, String lastname, double totalScore) {
        this.name = name;
        this.lastname = lastname;
        this.totalScore = totalScore;
    }

}
