package org.rest.masterlist.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserNotExistException extends RuntimeException {
    public UserNotExistException() {
        super("User does not exist");
        log.error("User does not exist");
        this.printStackTrace();
    }
}
