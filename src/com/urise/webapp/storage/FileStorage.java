package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
//        File getSearchFile = new File(directory, uuid);
        return new File(directory, uuid);
    }

    @Override
    protected void saveResume(Resume resume, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("IO Error: couldn't create file " + file.getAbsolutePath(), file.getName(), e);
        }
        updateResume(resume, file);
    }
//    // write resume into file in appropriate format
//    protected abstract void doWrite(Resume resume, OutputStream file) throws IOException;
//    // read resume from file in appropriate format
//    protected abstract Resume doRead(InputStream file) throws IOException, ClassNotFoundException;

    @Override
    protected void deleteResume(File file) {
        if (!file.delete()) {
            throw new StorageException("File deleting error", file.toString());
        }
    }
    @Override
    protected Resume getResume(File file) {
        Resume resume = null;
        try {
            return serializerStrategy.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException | ClassNotFoundException e) {
            throw new StorageException("getResume(): File read error", file.getName(), e);
        }
    }

    @Override
    protected void updateResume(Resume resume, File file) {
        try {
            serializerStrategy.doWrite(resume, new BufferedOutputStream( new FileOutputStream(file)));
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
            resumes.add(getResume(resumeFile));
        }
        return resumes;
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
