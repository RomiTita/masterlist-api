package org.rest.masterlist.model;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@Data
@Entity
public class Commission {

    private @Id @GeneratedValue int idCommission;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "carrer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Career career;

    @ManyToMany
    @JoinTable(name="cathedra_commission",
            joinColumns = @JoinColumn(name = "commission_id"),
            inverseJoinColumns = @JoinColumn(name = "cathedra_id"))
    private List<Cathedra> cathedras;

    public Commission(String name, Career career) {
        this.name = name;
        this.career = career;
    }

    public Commission() {

    }
}
