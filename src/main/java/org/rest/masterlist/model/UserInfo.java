package org.rest.masterlist.model;

import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class UserInfo {
    private @Id @GeneratedValue int idUser;
    private String name;
    private long facebookId;
    private String email;
    private String password;

    public UserInfo() {
    }

    public UserInfo(int id, String name, long facebookId, String email) {
        this.idUser = id;
        this.facebookId = facebookId;
        this.email = email;
        this.name = name;
    }

    public UserInfo(String name, long facebookId, String email, String password) {
        this.facebookId = facebookId;
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
