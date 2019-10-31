package org.rest.masterlist.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
public class Career {

    private @Id @GeneratedValue int idCareer;
    private String name;

    public Career(){}

    public Career(String name) {
        this.name = name;
    }
}
