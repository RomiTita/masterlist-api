package org.rest.masterlist.repository;

import org.rest.masterlist.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfo, Integer> {

    UserInfo findByFacebookId(long facebookId);
    UserInfo findByEmail(String email);
    UserInfo findByNameAndPassword(String name, String password);
}
