package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import com.urise.webapp.sql.SqlHelper;

import java.sql.DriverManager;
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
        sqlHelper.executeSqlTransaction(/*connectionFactory,*/
                "DELETE FROM resume WHERE uuid <> ?;",
                eps -> {
                    String abracadabra = "_@#$%^&*()_`~/\\''-+=:;?";
                    eps.setString(1, abracadabra);
                    return eps.execute();
                });
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.executeSqlTransaction(
                "INSERT INTO resume VALUES (?, ?);",
                eps -> {
                    eps.setString(1, resume.getUuid());
                    eps.setString(2, resume.getFullName());
                    return eps.executeUpdate();
                });
    }

    @Override
    public Resume get(String uuid) {
        return
                sqlHelper.executeSqlTransaction(
                        "SELECT * FROM resume WHERE resume.uuid = ?;",
                        eps -> {
                            eps.setString(1, uuid);
                            ResultSet resultSet = eps.executeQuery();
                            resultSet.next();
                            return new Resume(resultSet.getString("uuid").trim(), resultSet.getString("full_name"));
                        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.executeSqlTransaction(
                "DELETE FROM resume r WHERE r.uuid = ?;", eps -> {
                    eps.setString(1, uuid);
                    if (eps.executeUpdate() == 0) {
                        throw new SQLException(" delete(): " + "uuid", "24000");
                    }
                    return null;
                });
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.executeSqlTransaction(
                "UPDATE resumes.public.resume r SET full_name = ? WHERE r.uuid = ?;", eps -> {
                    eps.setString(1, resume.getFullName());
                    eps.setString(2, resume.getUuid());
                    if (eps.executeUpdate() == 0) {
                        throw new SQLException(" update(): " + "uuid", "24000");
                    }
                    return null;
                });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.executeSqlTransaction(
                "SELECT * FROM resume ORDER BY full_name, uuid;", eps -> {
                    List<Resume> list = new ArrayList<>();
                    ResultSet resultSet = eps.executeQuery();
                    while (resultSet.next()) {
                        list.add(new Resume(resultSet.getString("uuid").trim(), resultSet.getString("full_name")));
                    }
                    return list;
                });
    }

    @Override
    public int size() {
        return sqlHelper.executeSqlTransaction(
                "SELECT * FROM resume;", eps -> {
                    ResultSet resultSet = eps.executeQuery();
                    int size = 0;
                    while (resultSet.next()) {
                        size++;
                    }
                    return size;
                });
    }
}
