package com.urise.webapp.exception;

import com.urise.webapp.model.Resume;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(Resume resume) {
        this("Resume doesn't exist!", resume);
    }
    public NotExistStorageException(String message, Resume resume) {
        super(message, resume);
    }
}
