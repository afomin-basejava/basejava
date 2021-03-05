package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.sql.SqlHelper;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SqlStorage implements Storage {
    SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) throws SQLException {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.executePreparedStatement("DELETE FROM resume", PreparedStatement::execute);
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.<Void>executePreparedStatement(
                "INSERT INTO resume VALUES (?, ?)",
                eps -> {
                    eps.setString(1, resume.getUuid());
                    eps.setString(2, resume.getFullName());
                    eps.executeUpdate();
                    return null;
                });
        sqlHelper.<Void>executePreparedStatement(
                "INSERT INTO contact (resume_uuid, type, value) " +
                        "VALUES (?, ?, ?)",
                eps -> {
                    eps.setString(1, resume.getUuid());
                    for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                        eps.setString(2, String.valueOf(entry.getKey()));
                        eps.setString(3, String.valueOf(entry.getValue()));
                        eps.executeUpdate();
                    }
                    return null;
                });
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
                            Resume resume = new Resume(uuid, resultSet.getString("full_name"));
                            do {
                                String text = resultSet.getString("value");
                                ContactType contactType = ContactType.valueOf(resultSet.getString("type"));
                                resume.setContact(contactType, text);
                            } while (resultSet.next());
                            return resume;

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
        final String uuid = resume.getUuid();
        sqlHelper.executePreparedStatement(
                "UPDATE resume r SET full_name = ? WHERE r.uuid = ?", eps -> {
                    eps.setString(1, resume.getFullName());
                    eps.setString(2, uuid);
                    if (eps.executeUpdate() == 0) {
                        throw new NotExistStorageException(" update(): " + uuid);
                    }
                    return null;
                });
        sqlHelper.executePreparedStatement(
                "DELETE FROM contact c WHERE c.resume_uuid = ?", eps -> {
                    eps.setString(1, uuid);
                    if (eps.executeUpdate() == 0) {
                        throw new NotExistStorageException(" delete(): " + uuid);
                    }
                    return null;
                });
        sqlHelper.<Void>executePreparedStatement(
                "INSERT INTO contact (resume_uuid, type, value) " +
                        "VALUES (?, ?, ?)",
                eps -> {
                    eps.setString(1, uuid);
                    for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                        eps.setString(2, String.valueOf(entry.getKey()));
                        eps.setString(3, String.valueOf(entry.getValue()));
                        eps.executeUpdate();
                    }
                    return null;
                });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.executePreparedStatement(
                "SELECT * FROM resume ORDER BY full_name, uuid", eps -> {
                    List<Resume> resumes = new ArrayList<>();
                    ResultSet resultSet = eps.executeQuery();
                    while (resultSet.next()) {
                        String uuid = resultSet.getString("uuid");
                        Resume resume = new Resume(uuid, resultSet.getString("full_name"));
                        sqlHelper.executePreparedStatement("" + "SELECT c.type, c.value FROM contact c WHERE c.resume_uuid = ?",
                                epsContact -> {
                                    epsContact.setString(1, uuid);
                                    ResultSet resultSetContact = epsContact.executeQuery(); // FORWARD_ONLY by default
                                    if (!resultSetContact.next())
                                        throw new NotExistStorageException(uuid);
                                    do {
                                        String text = resultSetContact.getString("value");
                                        ContactType contactType = ContactType.valueOf(resultSetContact.getString("type"));
                                        resume.setContact(contactType, text);
                                    } while (resultSetContact.next());
                                    return resume;
                                }
                        );
                        resumes.add(resume);
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
}
