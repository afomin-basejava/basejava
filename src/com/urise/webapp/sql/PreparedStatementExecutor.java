package com.urise.webapp.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementExecutor<T> {
    T executePreparedStatement(PreparedStatement executablePreparedStatement) throws SQLException;
}
