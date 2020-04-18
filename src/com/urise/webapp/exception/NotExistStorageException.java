package com.urise.webapp.exception;

import com.urise.webapp.model.Resume;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String message, Resume resume) {
        super(message, resume);
    }
}
