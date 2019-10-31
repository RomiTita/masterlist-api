package org.rest.masterlist.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
@Entity
public class Cathedra {

    private @Id @GeneratedValue int idCathedra;
    private String name;
    private String code;

    @ManyToMany(mappedBy = "cathedras")
    private List<Commission> commissions;

    @ManyToMany
    @JoinTable(name="teacher_cathedra",
            joinColumns = @JoinColumn(name = "cathedra_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    private List<Teacher> teachers;

    public Cathedra(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public Cathedra() {

    }
}
