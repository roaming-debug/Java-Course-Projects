// --== CS400 Project One File Header ==--
// Name: Enze Ge
// CSL Username: enzeg
// Email: ege2@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader: N/A

import java.util.NoSuchElementException;
import java.util.LinkedList;

/**
 * Pair which stores key and value
 *
 * @param <KeyType>   KeyType
 * @param <ValueType> ValueType
 */
class Pair<KeyType, ValueType> {
    KeyType key;
    ValueType value;

    Pair(KeyType key, ValueType value) {
        this.key = key;
        this.value = value;
    }
}

/**
 * HashtableMap which could store a set of key-value pairs, has a good performance for searching and inserting
 *
 * @param <KeyType>   KeyType
 * @param <ValueType> ValueType
 */
public class HashtableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
    protected LinkedList<Pair<KeyType, ValueType>>[] data;

    @SuppressWarnings("unchecked")
    public HashtableMap(int capacity) {
        data = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            data[i] = new LinkedList<>();
        }
    }

    public HashtableMap() {
        this(20);
    }

    /**
     * dynamically grow your hashtable by doubling its capacity and rehashing,
     * whenever its load factor becomes greater than or equal to 75%. Define private helper
     * methods to better organize the code that does this.
     */
    @SuppressWarnings("unchecked")
    private void doubleCapacity() {
        float loadFactor = (float) size() / data.length;
        if (loadFactor >= 0.75) {
            LinkedList<Pair<KeyType, ValueType>>[] doubleData = new LinkedList[data.length * 2];
            for (int i = 0; i < doubleData.length; i++) {
                doubleData[i] = new LinkedList<>();
            }
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].size(); j++) {
                    Pair<KeyType, ValueType> movePair = data[i].get(j);
                    doubleData[movePair.key.hashCode() % doubleData.length].push(movePair);
                }
            }
            data = doubleData;
        }

    }

    /**
     * Insert the key and value pair into the HashtableMap and use chaining to handle hash collisions
     *
     * @param key   value of key
     * @param value value which is paired with the parameter key
     * @return false if the key is found in HashtableMap or key is null, true if putting the
     * pair key-value successful
     */
    @Override
    public boolean put(KeyType key, ValueType value) {
        if (key == null || containsKey(key)) {
            return false;
        }
        int hashIndex = key.hashCode() % data.length;
        data[hashIndex].push(new Pair<>(key, value));
        // check whether the capacity of data should be increased
        doubleCapacity();
        return true;
    }

    /**
     * Get the value of the corresponding key in the HashtableMap
     *
     * @param key key that will be searched for
     * @return value corresponding to key
     * @throws NoSuchElementException if the key is not found
     */
    @Override
    public ValueType get(KeyType key) throws NoSuchElementException {
        int hashIndex = key.hashCode() % data.length;
        for (int i = 0; i < data[hashIndex].size(); i++) {
            if (data[hashIndex].get(i).key.equals(key)) {
                return data[hashIndex].get(i).value;
            }
        }
        throw new NoSuchElementException();
    }


    /**
     * Get number of whole Key-Value pairs in the HashtableMap
     *
     * @return number of whole Key-Value pairs in the HashtableMap
     */
    @Override
    public int size() {
        int cnt = 0;
        for (int i = 0; i < data.length; i++) {
            cnt += data[i].size();
        }
        return cnt;
    }

    /**
     * Check if the key is contained in the HashtableMap
     *
     * @param key key that will be searched for
     * @return true if the key is found, false otherwise
     */
    @Override
    public boolean containsKey(KeyType key) {
        int hashIndex = key.hashCode() % data.length;
        if (data[hashIndex] != null) {
            for (int i = 0; i < data[hashIndex].size(); i++) {
                if (data[hashIndex].get(i).key.equals(key))
                    return true;
            }
        }
        return false;
    }

    /**
     * Remove the certain pair element whose key is equivalent to the parameter key
     *
     * @param key key that will be searched for
     * @return the value of pair which is corresponding to key value, null if the key is not found in the HashtableMap
     */
    @Override
    public ValueType remove(KeyType key) {
        int hashIndex = key.hashCode() % data.length;
        if (data[hashIndex] != null) {
            for (int i = 0; i < data[hashIndex].size(); i++) {
                if (data[hashIndex].get(i).key.equals(key)) {
                    ValueType removedVal = data[hashIndex].get(i).value;
                    data[hashIndex].remove(i);
                    return removedVal;
                }
            }
        }
        return null; // did not find the object
    }

    /**
     * Clears all the elements in the HashtableMap, but maintains the capacity of the HashtableMap
     */
    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        data = new LinkedList[data.length];
        for (int i = 0; i < data.length; i++)
            data[i] = new LinkedList<>();
    }
}
