package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private Path directory;

    public AbstractPathStorage(Path directory) {
        Objects.requireNonNull(directory, "directory must be no null");
        if (!Files.isDirectory(directory)) {
            throw new IllegalArgumentException(directory.toAbsolutePath() + "is not directory");
        }
        if (!Files.isWritable(directory)) {
            throw new IllegalArgumentException(directory.toAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    // write resume into file in appropriate format
    protected abstract void doWrite(Resume resume, OutputStream file) throws IOException;
    // read resume from file in appropriate format
    protected abstract Resume doRead(InputStream file) throws IOException, ClassNotFoundException;

    @Override
    protected Path getSearchKey(String uuid) {
        return Paths.get(String.valueOf(directory), uuid);
    }

    @Override
    protected void doSave(Resume resume, Path file) {
        try {
            Files.createFile(file);
        } catch (IOException e) {
            throw new StorageException("IO Error: couldn't create file " + file.toAbsolutePath(), null, e);
        }
        doUpdate(resume, file);
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("File deleting error", path.toString(), e);
        }
//        if (!path.toFile().delete()) {
//            throw new StorageException("File deleting error", path.toString());
//        }
    }
    @Override
    protected Resume doGet(Path file) {
        try {
            return doRead(new BufferedInputStream(Files.newInputStream(file)));
        } catch (IOException | ClassNotFoundException e) {
            throw new StorageException("getResume(): File read error", directory.toAbsolutePath().toString(), e);
        }
    }

    @Override
    protected void doUpdate(Resume resume, Path path) {
        try {
            doWrite(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("updateResume(..., ...) IO Error ", resume.getUuid(), e);
        }
    }

    @Override
    protected List<Resume> getAll() {
        try {
            return
                Files.list(directory)
                .map(this::doGet)
                .collect(Collectors.toList())
            ;
        } catch (IOException e) {
            throw new StorageException("IO Error getAll(): ", "directory " + directory, e);
        }
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("IO Error clear(): ", "directory " + directory, e);
        }
    }

    @Override
    public int size() {
        // return number of Resume's file in the directory (that must contain no subdirectories?)
        try {
            return (int) Files.list(directory).count();
        } catch (IOException e) {
            throw new StorageException("IO Error size(): ", "directory " + directory, e);
        }
    }

    @Override
    protected boolean isExistResume(Path path) {
        return Files.exists(path);
    }
}
