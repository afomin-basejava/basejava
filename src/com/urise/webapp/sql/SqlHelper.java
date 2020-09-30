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
            throw getTypeOfStorageException(e);
        }
    }

    private StorageException getTypeOfStorageException(SQLException e) {
        if (e.getSQLState().equals("23505")) {
            return new ExistStorageException(e.getMessage());
        }
        return new StorageException(e);
    }
}
