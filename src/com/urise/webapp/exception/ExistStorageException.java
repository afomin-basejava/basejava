package com.urise.webapp.exception;

import java.sql.SQLException;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        this("Resume already exists!", uuid);
    }
    public ExistStorageException(String message, String uuid) {
        super(message, uuid);
    }

    public ExistStorageException(SQLException e) {
        super(e);
    }
}
