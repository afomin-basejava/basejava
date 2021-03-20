package com.urise.webapp;

import com.urise.webapp.storage.SqlStorage;
import com.urise.webapp.storage.Storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    protected static final File PROPS = new File("D:\\basejava\\config\\resumes.properties");
    private static final Config INSTANCE = new Config();
    private final File storageDir;

    private final Storage sqlStorage;

    public static Config getINSTANCE() {
        return INSTANCE;
    }

    private Config() {
        try {
            InputStream inputStream = new FileInputStream(PROPS);
            Properties props = new Properties();
            props.load(inputStream);
            storageDir = new File(props.getProperty("storage.dir"));
            sqlStorage = new SqlStorage(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"));
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

}
