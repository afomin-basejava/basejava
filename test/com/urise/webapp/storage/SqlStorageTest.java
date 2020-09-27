package com.urise.webapp.storage;

import com.urise.webapp.Config;

public class SqlStorageTest extends AbstractStorageTest {
    public static final Storage sqlStorage= Config.getINSTANCE().getSqlStorage();
    public SqlStorageTest() {
//        super(new SqlStorage("jdbc:postgresql://localhost:5432/resumes", "postgres", "afomin"));
        super(sqlStorage);
    }
}