package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

    public AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must be no null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    // write resume into file in appropriate format
    protected abstract void doWrite(Resume resume, OutputStream file) throws IOException;
    // read resume from file in appropriate format
    protected abstract Resume doRead(InputStream file) throws IOException, ClassNotFoundException;

    @Override
    protected File getSearchKey(String uuid) {
//        File getSearchFile = new File(directory, uuid);
        return new File(directory, uuid);
    }
    @Override
    protected void doSave(Resume resume, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("IO Error: couldn't create file " + file.getAbsolutePath(), file.getName(), e);
        }
        doUpdate(resume, file);
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("File deleting error", file.toString());
        }
    }
    @Override
    protected Resume doGet(File file) {
        Resume resume = null;
        try {
            return doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException | ClassNotFoundException e) {
            throw new StorageException("getResume(): File read error", file.getName(), e);
        }
    }

    @Override
    protected void doUpdate(Resume resume, File file) {
        try {
            doWrite(resume, new BufferedOutputStream( new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("updateResume(..., ...) IO Error ", resume.getUuid(), e);
        }
    }

    @Override
    protected List<Resume> getAll() {
//        List<Resume> resumes =
        File[] fileList = directory.listFiles();
        if (fileList == null) {
            throw new StorageException("getAll(): Directory read error " + directory.getAbsolutePath(), null);
        }
        List<Resume> resumes = new ArrayList<>(fileList.length);
        for (File resumeFile : fileList) {
            resumes.add(doGet(resumeFile));
        }
        return resumes;
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (!file.isDirectory()) {
                    doDelete(file);
                }
            }
        }
    }

    @Override
    public int size() {
        // return number of Resume's file in the directory (that must contain no subdirectories?)
        String[] list = directory.list();
        if (list == null) {
            throw new StorageException("size(): Directory read error ", null);
        }
        return list.length;
    }

    @Override
    protected boolean isExistResume(File file) {
        return file.exists();
    }
}
