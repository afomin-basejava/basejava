package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.sql.SqlHelper;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) throws SQLException {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.executePreparedStatement(
                "DELETE FROM resume",
                PreparedStatement::execute);
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.executePreparedStatement(
                "INSERT INTO resume VALUES (?, ?)",
                eps -> {
                    eps.setString(1, resume.getUuid());
                    eps.setString(2, resume.getFullName());
                    return eps.executeUpdate();
                });
    }

    @Override
    public Resume get(String uuid) {
        return
                sqlHelper.executePreparedStatement(
                        "SELECT * FROM resume WHERE resume.uuid = ?",
                        eps -> {
                            eps.setString(1, uuid);
                            ResultSet resultSet = eps.executeQuery();
                            if (!resultSet.next())
                                throw new NotExistStorageException(uuid);
                            return new Resume(resultSet.getString("uuid"), resultSet.getString("full_name"));

                        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.executePreparedStatement(
                "DELETE FROM resume r WHERE r.uuid = ?", eps -> {
                    eps.setString(1, uuid);
                    if (eps.executeUpdate() == 0) {
                        throw new NotExistStorageException(" delete(): " + uuid);
                    }
                    return null;
                });
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.executePreparedStatement(
                "UPDATE resume r SET full_name = ? WHERE r.uuid = ?", eps -> {
                    eps.setString(1, resume.getFullName());
                    eps.setString(2, resume.getUuid());
                    if (eps.executeUpdate() == 0) {
                        throw new NotExistStorageException(" update(): " + resume.getUuid());
                    }
                    return null;
                });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.executePreparedStatement(
                "SELECT * FROM resume ORDER BY full_name, uuid", eps -> {
                    List<Resume> list = new ArrayList<>();
                    ResultSet resultSet = eps.executeQuery();
                    while (resultSet.next()) {
                        list.add(new Resume(resultSet.getString("uuid"), resultSet.getString("full_name")));
                    }
                    return list;
                });
    }

    @Override
    public int size() {
        return sqlHelper.executePreparedStatement(
                "SELECT count(*) FROM resume", eps -> {
                    ResultSet resultSet = eps.executeQuery();
                    return resultSet.next() ? resultSet.getInt(1) : 0;
                });
    }
}
