package com.urise.webapp.sql;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.StorageException;
import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public <T> T executePreparedStatement(String sqlStatement, PreparedStatementExecutor<T> eps) {
        try (PreparedStatement preparedStatement = connectionFactory.getConnection().prepareStatement(sqlStatement)) {
            return eps.executePreparedStatement(preparedStatement);
        } catch (SQLException e) {
            throw getTypeOfStorageException(e);
        }
    }

    public <T> T transactionalExecute(SqlTransaction<T> executor) {
        try (Connection conn = connectionFactory.getConnection()) {
            try {
                conn.setAutoCommit(false);
                T result = executor.execute(conn);
                conn.commit();
                return result;
            } catch (SQLException e) {
                conn.rollback();
                throw getTypeOfStorageException(e);
            }
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }


    private StorageException getTypeOfStorageException(SQLException e) {
        if (e instanceof PSQLException) {
            if (e.getSQLState().equals("23505")) {
                return new ExistStorageException(e);
            }
        }
        return new StorageException(e);
    }
}
