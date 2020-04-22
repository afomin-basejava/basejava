package com.urise.webapp.storage;

public class SortedArrayStorageTest extends AbstractArrayStorageTest{
    private static Class STORAGE_TYPE = SortedArrayStorage.class;
//    Storage storage = new SortedArrayStorage();
    public SortedArrayStorageTest() throws InstantiationException, IllegalAccessException {
        super(STORAGE_TYPE);
    }
}