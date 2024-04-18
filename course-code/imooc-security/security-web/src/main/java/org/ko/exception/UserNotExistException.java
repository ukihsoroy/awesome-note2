package org.ko.exception;

public class UserNotExistException extends RuntimeException {

    public String id;

    public UserNotExistException(String id) {
        super("user not exception");
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
