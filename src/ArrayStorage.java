import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    static final int CAPACITY = 4;
    static int size = 0;
    Resume[] storage = new Resume[CAPACITY];

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        if (size < CAPACITY) {
            storage[size++] = r;
        } else {
            System.out.println("ArrayStorage: save attempt -" + r + "- out of storage memory limit!");
        }
    }

    Resume get(String uuid) {
        for (int resume = 0; resume < size; resume++) {
            if (storage[resume].uuid.equals(uuid)) {
                return storage[resume];
            }
        }
        return null;
    }

//    Resume getDummyResume(String uuid) {
//        Resume dummyResume = new Resume();
//        dummyResume.uuid = uuid + " - doesn't exist!";
//        return dummyResume;
//    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                for (int j = i; j < size - 1; j ++) {
                    storage[j] = storage[j + 1];
                }
                storage[size - 1] = null;
                size--;
                break;
            }
        }
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
