package com.urise.webapp;

import com.urise.webapp.storage.SqlStorage;
import com.urise.webapp.storage.Storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    protected static final File PROPS = new File("config\\resumes.properties");
    private static final Config INSTANCE = new Config();
    private final Properties props = new Properties();
    private final File storageDir;
    private final String dbUrl;
    private final String dbUser;
    private String dbPassword;

    private Storage sqlStorage;

    public static Config getINSTANCE() {
        return INSTANCE;
    }

    private Config() {
        try {
            InputStream inputStream = new FileInputStream(PROPS);
            props.load(inputStream);
            storageDir = new File(props.getProperty("storage.dir"));
            dbUrl = props.getProperty("db.url");
            dbUser = props.getProperty("db.user");
            dbPassword = props.getProperty("db.password");
            sqlStorage = new SqlStorage(dbUrl, dbUser, dbPassword);
        } catch (Exception e) {
            throw new IllegalStateException("Invalid config file: " + PROPS.getAbsolutePath());
        }
    }

    public File getStorageDir() {
        return storageDir;
    }

    public Storage getSqlStorage() {
        return sqlStorage;
    }

    public static void main(String[] args) {
        new Config();
    }

}
