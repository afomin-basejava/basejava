package com.urise.webapp.exception;

import com.urise.webapp.model.Resume;

public class StorageException extends RuntimeException {
    private String uuid;
    private String message;

    public StorageException(String message) {
        super(message);
        this.message = message;
    }

    public StorageException(String message, Resume resume) {
        super(message);
        this.uuid = resume.getUuid();
        this.message = message;
    }
}
