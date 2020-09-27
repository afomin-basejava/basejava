package com.urise.webapp.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ExecutePreparedStatement <T> {
    T executePreparedStatement(PreparedStatement executablePreparedStatements) throws SQLException;
}
