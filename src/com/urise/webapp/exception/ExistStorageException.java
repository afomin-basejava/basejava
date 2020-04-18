package com.urise.webapp.exception;

import com.urise.webapp.model.Resume;

public class ExistStorageException extends StorageException {

    public ExistStorageException(String message) {
        super(message);
    }

    public ExistStorageException(String message, Resume resume) {
        super(message, resume);
    }
}
