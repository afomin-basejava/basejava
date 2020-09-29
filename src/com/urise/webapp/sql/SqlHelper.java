package com.urise.webapp.sql;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    private final Connection connection;

    public SqlHelper(ConnectionFactory connectionFactory) throws SQLException {
        connection = connectionFactory.getConnection();
    }

    public <T> T executePreparedStatement(String sqlStatement, PreparedStatementExecutor<T> eps) {
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)
        ) {
            return eps.executePreparedStatement(preparedStatement);
        } catch (SQLException e) {
            throw getTypeOfStorageException(e, sqlStatement);
        }
    }

    private StorageException getTypeOfStorageException(SQLException e, String uuid) {
        switch (e.getSQLState()) {
            case "23505":
                return new ExistStorageException(uuid);
            case "others":
            default:
                return new StorageException(e.getMessage() + " : " + uuid);
        }
    }
}
