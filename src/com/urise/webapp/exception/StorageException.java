package com.urise.webapp.exception;

public class StorageException extends RuntimeException {
    private String uuid;
    private String message;

    public StorageException(String message, String uuid) {
        super(message + " " + uuid);
        this.uuid = uuid;
        this.message = message;
    }

    public StorageException(String message, String uuid, Exception e) {
        super(message, e);
        this.uuid = uuid;
    }
}
