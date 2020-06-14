package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

    public AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must be non null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    protected File getSearchKey(String uuid) {
//        File getSearchFile = new File(directory, uuid);
        return new File(directory, uuid);
    }

    @Override
    protected void saveResume(Resume resume, File file) {
        try {
//            file.createNewFile();
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO Error", file.getName(), e);
        }
    }
    // write resume into file in appropriate format
    protected abstract void doWrite(Resume resume, File file) throws IOException;

    @Override
    protected void deleteResume(File file) {
        file.delete();
    }

    @Override
    protected Resume getResume(File file) {
//        Resume resume = doRead(file);
        return doRead(file);
    }
    // read resume from file in appropriate format
    protected abstract Resume doRead(File file);

    @Override
    protected void updateResume(Resume resume, File file) {
        try {
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO Error", file.getName(), e);
        }
    }

    @Override
    protected List<Resume> getAll() {
//        List<Resume> resumes =
        return Arrays.stream(directory.listFiles())
                .filter(f -> !f.isDirectory())
                .map(this::doRead)
                .collect(Collectors.toList());
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (!file.isDirectory()) {
                    deleteResume(file);
                }
            }
        }
    }

    @Override
    public int size() {
        // return number of Resume's file in the directory (that must contain no subdirectories?)
        return directory.list().length;
    }

    @Override
    protected boolean isExistResume(File file) {
        return file.exists();
    }
}
