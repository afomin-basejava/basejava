package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.serializerstrategy.SerializerStrategy;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FileStorage extends AbstractStorage<File> {
    private File directory;
    private SerializerStrategy serializerStrategy;

    public FileStorage(File directory, SerializerStrategy serializerStrategy) {
        Objects.requireNonNull(directory, "directory must be no null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
        this.serializerStrategy = serializerStrategy;
    }

    @Override
    protected File getSearchKey(String uuid) {
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
            return serializerStrategy.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException | ClassNotFoundException e) {
            throw new StorageException("getResume(): File read error", file.getName(), e);
        }
    }

    @Override
    protected void doUpdate(Resume resume, File file) {
        try {
            serializerStrategy.doWrite(resume, new BufferedOutputStream( new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("updateResume(..., ...) IO Error ", resume.getUuid(), e);
        }
    }

    @Override
    protected List<Resume> getAll() {
        return
                Arrays.stream(listFiles())
                .map(this::doGet)
                .collect(Collectors.toList());
    }

    @Override
    public void clear() {
        for (File file : listFiles()) {
            if (!file.isDirectory()) {
                doDelete(file);
            }
        }
    }

    @Override
    public int size() {
        // return number of Resume's file in the directory (that must contain no subdirectories?)
        return listFiles().length;
    }

    @Override
    protected boolean isExistResume(File file) {
        return file.exists();
    }

    private File[] listFiles() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("FileStorage - Directory read error: ", directory.toString());
        }
        return files;
    }

}
