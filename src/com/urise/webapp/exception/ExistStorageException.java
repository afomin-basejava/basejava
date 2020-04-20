package com.urise.webapp.exception;

import com.urise.webapp.model.Resume;

public class ExistStorageException extends StorageException {
    public ExistStorageException(Resume resume) {
        this("Resume already exists!", resume);
    }
    public ExistStorageException(String message, Resume resume) {
        super(message, resume);
    }
}
