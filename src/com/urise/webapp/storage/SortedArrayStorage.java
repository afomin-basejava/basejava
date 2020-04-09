package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume resume) {
        if (size < CAPACITY) {
            int insertPoint = indexOf(resume);
            if (storage[insertPoint] == null) {
                storage[insertPoint] = resume;
                size++;
                return;
            }
            if (!storage[insertPoint].getUuid().equals(resume.getUuid())) {
                System.arraycopy(storage, insertPoint, storage, insertPoint + 1, AbstractArrayStorage.size - insertPoint);
                storage[insertPoint] = resume;
                size++;
            } else {
                System.out.println("Resume " + resume.getUuid() + " duplicate doesn't allowed!");
            }
        } else {
            System.out.println("Resume: save attempt -" + resume + "- out of storage memory limit!");
        }
    }

    @Override
    public Resume get(Resume resume) {
        int index = indexOf(resume);
        if (storage[index] != null) {
            if (storage[index].getUuid().equals(resume.getUuid())) {
                return storage[index];
            }
            else {
                System.out.println("Resume " + resume.getUuid() + " doesn't exist!");
            }
        } else {
            System.out.println("Resume " + resume.getUuid() + " doesn't exist!");
        }
        return null;
    }

    @Override
    public void delete(Resume resume) {
        int index = indexOf(resume);
        if (storage[index] != null) {
            if (storage[index].getUuid().equals(resume.getUuid())) {
                if (size - 1 - index >= 0) {
                    System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
                }
                storage[size - 1] = null;
                size--;
            }
            else {
                System.out.println("Resume " + resume.getUuid() + " doesn't exist - deletion impossible!");
            }
        } else {
            System.out.println("Resume " + resume.getUuid() + " doesn't exist - deletion impossible!");
        }
    }

    @Override
    public void update(Resume resume) {
        int index = indexOf(resume);
        if (storage[index] != null) {
            if (storage[index].getUuid().equals(resume.getUuid())) {
                storage[index] = resume;
            }
            else {
                System.out.println("Resume " + resume.getUuid() + " doesn't exist - update impossible!");
            }
        } else {
            System.out.println("Resume " + resume.getUuid() + " doesn't exist - update impossible!");
        }
    }

    @Override
    public int indexOf(Resume resume) {
        int insertPoint = Arrays.binarySearch(storage, 0, size(), resume);
        if (insertPoint < 0) {
            insertPoint = -insertPoint - 1;
        }
        return insertPoint;
    }
}
