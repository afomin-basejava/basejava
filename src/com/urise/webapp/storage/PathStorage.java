package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.serializer.StreamSerializer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;
    private final StreamSerializer streamSerializer;

    public PathStorage(Path directory, StreamSerializer streamSerializer) {
        Objects.requireNonNull(directory, "directory must be no null");
        if (!Files.isDirectory(directory)) {
            throw new IllegalArgumentException(directory.toAbsolutePath() + "is not directory");
        }
        if (!Files.isWritable(directory)) {
            throw new IllegalArgumentException(directory.toAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
        this.streamSerializer = streamSerializer;
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
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
    }

    @Override
    protected Resume doGet(Path file) {
        try {
            return streamSerializer.doRead(new BufferedInputStream(Files.newInputStream(file)));
        } catch (IOException | ClassNotFoundException e) {
            throw new StorageException("getResume(): File read error", directory.toAbsolutePath().toString(), e);
        }
    }

    @Override
    protected void doUpdate(Resume resume, Path path) {
        try {
            streamSerializer.doWrite(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("updateResume(..., ...) IO Error ", resume.getUuid(), e);
        }
    }

    @Override
    protected List<Resume> getAll() {
            return
                pathes()
                .map(this::doGet)
                .collect(Collectors.toList())
            ;
    }

    @Override
    public void clear() {
        pathes().forEach(this::doDelete);
    }

    @Override
    public int size() {
        // return number of Resume's file in the directory (that must contain no subdirectories?)
            return (int) pathes().count();
    }

    @Override
    protected boolean isExistResume(Path path) {
        return Files.exists(path);
    }

    private Stream<Path> pathes() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("IO Error: ", "directory " + directory, e);
        }
    }
}
