import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int CAPACITY = 4;
    private static int size = 0;
    private Resume[] storage = new Resume[CAPACITY];

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume r) {
        if (size < CAPACITY) {
            if (indexOf(r.getUuid()) < 0) {
                storage[size++] = r;
            } else {
                System.out.println("Resume " + r.getUuid() + " duplicate doesn't allowed!");
            }
        } else {
            System.out.println("ArrayStorage: save attempt -" + r + "- out of storage memory limit!");
        }
    }

    Resume get(String uuid) {
        int index = indexOf(uuid);
        if (index >= 0) {
            return storage[index];
        }
        System.out.println("Resume " + uuid + " doesn't exist!");
        return null;
    }

//    Resume getDummyResume(String uuid) {
//        Resume dummyResume = new Resume();
//        dummyResume.uuid = uuid + " - doesn't exist!";
//        return dummyResume;
//    }

    void delete(String uuid) {
        int index = indexOf(uuid);
        if (index >= 0) {
            for (int j = index; j < size - 1; j ++) {
                storage[j] = storage[j + 1];
            }
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Resume " + uuid + " doesn't exist - deletion impossible!");
        }
    }

    void update(Resume resume) {
        int index = indexOf(resume.getUuid());
        if (index >= 0) {
            // TODO update
            resume.setUuid(resume.getUuid());
            storage[index] = resume;
        } else {
            System.out.println("Resume " + resume.getUuid() + " doesn't exist - update impossible!");
        }
    }

    private int indexOf(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }
}
