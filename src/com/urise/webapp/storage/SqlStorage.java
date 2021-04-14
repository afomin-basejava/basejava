package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.AbstractSection;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.SectionType;
import com.urise.webapp.sql.SqlHelper;
import com.urise.webapp.util.GsonParser;

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
            insertSections(resume, connection);
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
            deleteSections(resume, connection);
            insertSections(resume, connection);
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
                sqlHelper.transactionalExecute(connection -> {
                    try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM resume WHERE resume.uuid = ?")) {
                        ps.setString(1, uuid);
                        ResultSet rs = ps.executeQuery();
                        if (!rs.next())
                            throw new NotExistStorageException(uuid);
                        return prepareResume(connection, new Resume(rs.getString(1), rs.getString(2)));
                    }
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
        return
                sqlHelper.transactionalExecute(this::executeAllSorted);
    }

    @Override
    public int size() {
        return sqlHelper.executePreparedStatement(
                "SELECT count(*) FROM resume", eps -> {
                    ResultSet resultSet = eps.executeQuery();
                    return resultSet.next() ? resultSet.getInt(1) : 0;
                });
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

    private void insertSections(Resume resume, Connection connection) throws SQLException {
        try (PreparedStatement eps = connection.prepareStatement("INSERT INTO section (resume_uuid, type, value) VALUES (?, ?, ?)")) {
            for (Map.Entry<SectionType, AbstractSection> entry : resume.getSections().entrySet()) {
                eps.setString(1, resume.getUuid());
                eps.setString(2, String.valueOf(entry.getKey()));
                eps.setString(3, GsonParser.write(entry.getValue(), AbstractSection.class));
                eps.addBatch();
            }
            eps.executeBatch();
        }
    }

    private void deleteContacts(Resume resume, Connection connection) throws SQLException {
        deleteResumePartition(resume, connection, "DELETE FROM contact c WHERE c.resume_uuid = ?");
    }

    private void deleteSections(Resume resume, Connection connection) throws SQLException {
        deleteResumePartition(resume, connection, "DELETE FROM section c WHERE c.resume_uuid = ?");
    }

    private void deleteResumePartition(Resume resume, Connection connection, String sql) throws SQLException {
        try (PreparedStatement eps = connection.prepareStatement(sql)) {
            eps.setString(1, resume.getUuid());
            if (eps.executeUpdate() == 0) {
                throw new NotExistStorageException(sql + " " + resume.getUuid());
            }
        }
    }

    private Resume prepareResume(Connection connection, Resume resume) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM contact c WHERE c.resume_uuid = ?")) {
            ps.setString(1, resume.getUuid());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resume.setContact(ContactType.valueOf(rs.getString("type")), rs.getString("value"));
            }
        }
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM section s WHERE s.resume_uuid = ?")) {
            ps.setString(1, resume.getUuid());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resume.setSection(SectionType.valueOf(rs.getString("type")),
                                  GsonParser.read(rs.getString("value"),
                                  AbstractSection.class));
            }
        }
        return resume;
    }

    private List<Resume> executeAllSorted(Connection connection) throws SQLException {
        final List<Resume> resumes = new ArrayList<>();
        try (PreparedStatement selectResumes = connection.prepareStatement("SELECT * FROM resume ORDER BY full_name, uuid")) {
            ResultSet resumesResultSet = selectResumes.executeQuery();
            while (resumesResultSet.next()) {
                resumes.add(prepareResume(connection,
                            new Resume(resumesResultSet.getString("uuid"),
                            resumesResultSet.getString(2)))
                );
            }
        }
        return resumes;
    }
}
