package com.urise.webapp.exception;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        this("Resume already exists!", uuid);
    }
    public ExistStorageException(String message, String uuid) {
        super(message, uuid);
    }
}
