package com.urise.webapp.exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        this("Resume doesn't exist!", uuid);
    }
    public NotExistStorageException(String message, String uuid) {
        super(message, uuid);
    }
}
