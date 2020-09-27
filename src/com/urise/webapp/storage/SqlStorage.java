package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.sql.ConnectionFactory;
import com.urise.webapp.sql.ExecutePreparedStatement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    public final ConnectionFactory connectionFactory;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
        executeSqlTransaction(connectionFactory,
                "DELETE FROM resume WHERE uuid <> ?;",
                eps -> {
                    String abracadabra = "_@#$%^&*()_`~/\\''-+=:;?";
                    eps.setString(1, abracadabra);
                    return eps.execute();
                });
//        try (Connection connection = connectionFactory.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM resume WHERE uuid <> ?;")
//        ) {

//        } catch (SQLException e) {
//            throw getTypeOfStorageException(e, "clear(): ");
//        }
    }

    @Override
    public void save(Resume resume) {
        executeSqlTransaction(connectionFactory, "INSERT INTO resume VALUES (?, ?);",
                eps -> {
                    eps.setString(1, resume.getUuid());
                    eps.setString(2, resume.getFullName());
                    return
                            eps.executeUpdate();
//                    return null;
                });
        /*try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO resume VALUES (?, ?);")
        ) {
            preparedStatement.setString(1, resume.getUuid());
            preparedStatement.setString(2, resume.getFullName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw getTypeOfStorageException(e, resume.getUuid());
        }*/
    }

    @Override
    public Resume get(String uuid) {
        return
                executeSqlTransaction(connectionFactory, "SELECT * FROM resume WHERE resume.uuid = ?;",
                        eps -> {
                            eps.setString(1, uuid);
                            ResultSet resultSet = eps.executeQuery();
                            resultSet.next();
                            return new Resume(resultSet.getString("uuid").trim(), resultSet.getString("full_name"));
                        });
//        try (Connection connection = connectionFactory.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM resume WHERE resume.uuid = ?;")
//        ) {
//            preparedStatement.setString(1, uuid);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            resultSet.next();
//            return new Resume(resultSet.getString("uuid").trim(), resultSet.getString("full_name"));
//        } catch (SQLException e) {
//            throw getTypeOfStorageException(e, uuid);
//        }
    }

    @Override
    public void delete(String uuid) {
        executeSqlTransaction(connectionFactory, "DELETE FROM resume r WHERE r.uuid = ?;", eps -> {
            eps.setString(1, uuid);
            if (eps.executeUpdate() == 0) {
                throw new SQLException(" delete(): " + "uuid", "24000");
            }
            return null;
        });
        /*try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM resume r WHERE r.uuid = ?;")
        ) {
            preparedStatement.setString(1, uuid);
            if (preparedStatement.executeUpdate() == 0) {
                throw new SQLException(" delete(): " + "uuid", "24000");
            }
        } catch (SQLException e) {
                        throw getTypeOfStorageException(e, uuid);
        }*/
    }

    @Override
    public void update(Resume resume) {
        executeSqlTransaction(connectionFactory, "UPDATE resumes.public.resume r SET full_name = ? WHERE r.uuid = ?;", eps -> {
            eps.setString(1, resume.getFullName());
            eps.setString(2, resume.getUuid());
            if (eps.executeUpdate() == 0) {
                throw new SQLException(" update(): " + "uuid", "24000");
            }
            return null;
        });
        /*try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE resumes.public.resume r SET full_name = ? WHERE r.uuid = ?;")
        ) {
            preparedStatement.setString(1, resume.getFullName());
            preparedStatement.setString(2, resume.getUuid());
            if (preparedStatement.executeUpdate() == 0) {
                throw new SQLException(" update(): " + "uuid", "24000");
            }
        } catch (SQLException e) {
            throw getTypeOfStorageException(e, resume.getUuid());
        }*/
    }

    @Override
    public List<Resume> getAllSorted() {
        return executeSqlTransaction(connectionFactory, "SELECT * FROM resume ORDER BY full_name, uuid;", eps -> {
            List<Resume> list = new ArrayList<>();
            ResultSet resultSet = eps.executeQuery();
            while (resultSet.next()) {
                list.add(new Resume(resultSet.getString("uuid").trim(), resultSet.getString("full_name")));
            }
            return list;
        });
        /*try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM resume ORDER BY full_name, uuid;")
        ) {
            List<Resume> list = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Resume(resultSet.getString("uuid").trim(), resultSet.getString("full_name")));
            }
            return list;
        } catch (SQLException e) {
            throw getTypeOfStorageException(e, "getAllSorted(): ");
        }*/
    }

    @Override
    public int size() {
        return executeSqlTransaction(connectionFactory, "SELECT * FROM resume;", eps -> {
            ResultSet resultSet = eps.executeQuery();
            int size = 0;
            while (resultSet.next()) {
                size++;
            }
            return size;
        });
    }
        /*try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM resume;")
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            int size = 0;
            while (resultSet.next()) {
                size++;
            }
            return size;
        } catch (SQLException e) {
            throw getTypeOfStorageException(e, "size(): ");
        }
    }*/

    static StorageException getTypeOfStorageException(SQLException e, String uuid) {
        switch (e.getSQLState()) {
            case "23505":
                return new ExistStorageException(uuid);
            case "24000":
                return new NotExistStorageException(uuid);
            case "others":
            default:
                return new StorageException(uuid);
        }
    }

    private <T> T executeSqlTransaction(ConnectionFactory connectionFactory, String sqlStatement, ExecutePreparedStatement<T> eps) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)
        ) {
            return eps.executePreparedStatement(preparedStatement);
        } catch (SQLException e) {
            throw getTypeOfStorageException(e, sqlStatement);
        }
    }
}
