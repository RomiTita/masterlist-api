package org.rest.masterlist.controller;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.rest.masterlist.exception.EntityNotFoundException;
import org.rest.masterlist.exception.UserNotExistException;
import org.rest.masterlist.model.Comment;
import org.rest.masterlist.model.UserInfo;
import org.rest.masterlist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping("users/getAllUsers")
    public List<UserInfo> getAllUsers() {
        log.info("trying to GET all users");
        return repository.findAll();
    }

    @PostMapping("users/createUserInfo")
    public String createUserInfo(@RequestBody UserInfo user) {
        log.info("trying to create an user");
        //UserInfo userInDB = repository.findByFacebookId(user.getFacebookId());
        UserInfo userInDB = repository.findByEmail(user.getEmail());
        if (userInDB == null) {
            //user.setName(user.getEmail().split("@")[0]); //tiene que ser obligatorio mandar el mail si el usuario no existe
            return repository.save(user) != null ? "Success" : "Error";
        } else {
            return "User exists";
        }
    }

    @PostMapping("users/userExists")
    public Result userExists(@RequestBody UserInfo user) {
        log.info("trying to look for an user");
        //TODO decrypt pass and username
        UserInfo userInDB = repository.findByNameAndPassword(user.getName(), user.getPassword());
        if( userInDB != null) return new Result(userInDB.getIdUser(), true);
        else return new Result(0, false);
    }

    @GetMapping("users/getUserInfo")
    public UserInfo getUserInfo(@RequestParam int idUser) {
        log.info("trying to get the user");
        Optional<UserInfo> userInfo = repository.findById(idUser);

        if (userInfo.isPresent()) {
            UserInfo userFound = userInfo.get();
            return new UserInfo(userFound.getIdUser(), userFound.getName(), userFound.getFacebookId(), userFound.getEmail());
        }
        else throw new EntityNotFoundException(idUser, "UserInfo not found");
    }

    private class Result{
        private int id;
        private boolean result;
        public Result (int id, boolean result) {
            this.id = id;
            this.result = result;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isResult() {
            return result;
        }

        public void setResult(boolean result) {
            this.result = result;
        }
    }

}
