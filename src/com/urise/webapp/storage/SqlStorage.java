package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.sql.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SqlStorage implements Storage {
    final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    public SqlHelper getSqlHelper() {
        return sqlHelper;
    }

    //(https://dzone.com/articles/removing-duplicate-code-with-lambda-expressions)
    @Override
    public void save(Resume resume) {
        sqlHelper.transactionalExecute(connection -> {
            try (PreparedStatement ps = connection.prepareStatement("INSERT INTO resume VALUES (?, ?)")) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, resume.getFullName());
                ps.execute();
            }
            insertContacts(resume, connection);
            return null;
        });
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.transactionalExecute(connection -> {
            try (PreparedStatement eps = connection.prepareStatement("UPDATE resume r SET full_name = ? WHERE r.uuid = ?")) {
                eps.setString(1, resume.getFullName());
                eps.setString(2, resume.getUuid());
                if (eps.executeUpdate() == 0) {
                    throw new NotExistStorageException(" update(): " + resume.getUuid());
                }
            }
            deleteContacts(resume, connection);
            insertContacts(resume, connection);
            return null;
        });
    }

    @Override
    public void clear() {
        sqlHelper.executePreparedStatement("TRUNCATE  resume  CASCADE", PreparedStatement::execute);
    }

    @Override
    public Resume get(String uuid) {
        return
                sqlHelper.executePreparedStatement("" +
                                " SELECT * FROM resume r" +
                                "   LEFT JOIN contact c" +
                                "       ON r.uuid = c.resume_uuid" +
                                "   WHERE r.uuid = ?",
                        eps -> {
                            eps.setString(1, uuid);
                            ResultSet resultSet = eps.executeQuery(); // FORWARD_ONLY by default
                            if (!resultSet.next())
                                throw new NotExistStorageException(uuid);
                            return getResume(resultSet, new Resume(uuid, resultSet.getString("full_name")));

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
                }
        );
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.executePreparedStatement(
                "SELECT * FROM resume ORDER BY full_name, uuid", eps -> {
                    List<Resume> resumes = new ArrayList<>();
                    ResultSet resultSet = eps.executeQuery();
                    while (resultSet.next()) {
                        String uuid = resultSet.getString("uuid");
                        resumes.add(sqlHelper.executePreparedStatement("" + "SELECT c.type, c.value FROM contact c WHERE c.resume_uuid = ?",
                                epsContact -> {
                                    epsContact.setString(1, uuid);
                                    ResultSet resultSetContact = epsContact.executeQuery(); // FORWARD_ONLY by default
                                    if (!resultSetContact.next())
                                        throw new NotExistStorageException(uuid);
                                    return getResume(resultSetContact, new Resume(uuid, resultSet.getString("full_name")));
                                }
                        ));
                    }
                    return resumes;
                }
        );
    }

    @Override
    public int size() {
        return sqlHelper.executePreparedStatement(
                "SELECT count(*) FROM resume", eps -> {
                    ResultSet resultSet = eps.executeQuery();
                    return resultSet.next() ? resultSet.getInt(1) : 0;
                });
    }

    private Resume getResume(ResultSet resultSet, Resume resume) throws SQLException {
        do {
            String text = resultSet.getString("value");
            ContactType contactType = ContactType.valueOf(resultSet.getString("type"));
            resume.setContact(contactType, text);
        } while (resultSet.next());
        return resume;
    }

    private void insertContacts(Resume resume, Connection connection) throws SQLException {
        try (PreparedStatement eps = connection.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?, ?, ?)")) {
            for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                eps.setString(1, resume.getUuid());
                eps.setString(2, String.valueOf(entry.getKey()));
                eps.setString(3, String.valueOf(entry.getValue()));
                eps.addBatch();
            }
            eps.executeBatch();
        }
    }

    private void deleteContacts(Resume resume, Connection connection) throws SQLException {
        try (PreparedStatement eps = connection.prepareStatement("DELETE FROM contact c WHERE c.resume_uuid = ?")) {
            eps.setString(1, resume.getUuid());
            if (eps.executeUpdate() == 0) {
                throw new NotExistStorageException(" delete(): " + resume.getUuid());
            }
        }
    }
}
