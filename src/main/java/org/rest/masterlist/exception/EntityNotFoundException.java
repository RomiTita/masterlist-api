package org.rest.masterlist.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Integer id) {
        super("Could not find entity " + id);
        log.error("Could not find entity " + id);
        this.printStackTrace();
    }

    public EntityNotFoundException(Integer id, String message) {
        super(message + " " + id);
        log.error(message + " " + id);
        this.printStackTrace();
    }
}
