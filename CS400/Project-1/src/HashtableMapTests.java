// --== CS400 Project One File Header ==--
// Name: Enze Ge
// CSL Username: enzeg
// Email: ege2@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader: N/A

import java.util.NoSuchElementException;

/**
 * The tester for the HashtableMap
 */
public class HashtableMapTests {
    /**
     * The main function for tester
     *
     * @param args command line options
     */
    public static void main(String[] args) {
        if (!test1() || !test2() || !test3() || !test4() || !test5()) {
            System.err.println("Some tests failed");
        } else
            System.out.println("All tests passed");
    }

    /**
     * test constructors
     *
     * @return true if no bugs detected, false otherwise
     */
    public static boolean test1() {
        HashtableMap<Integer, Integer> m = new HashtableMap<>();
        if (m.size() != 0 || m.data.length != 20) {
            return false;
        }
        m = new HashtableMap<>(30);
        if (m.size() != 0 || m.data.length != 30) {
            return false;
        }
        return true;
    }

    /**
     * Check correctness of the implementation of the functions put() and get()
     *
     * @return true if no bugs detected, false otherwise
     */
    public static boolean test2() {
        HashtableMap<Integer, Integer> m = new HashtableMap<>();

        // Check whether the implementation used chaining to avoid hash collisions
        if (!m.put(1, 2) || !m.data[1].get(0).key.equals(1) || !m.data[1].get(0).value.equals(2)) {
            return false;
        }
        if (!m.get(1).equals(2)) {
            return false;
        }
        if (m.put(1, 3) || m.put(null, null)) {
            return false;
        }

        if (!m.put(21, 2) || !m.data[1].get(1).key.equals(1) || !m.data[1].get(1).value.equals(2)
                || !m.data[1].get(0).key.equals(21) || !m.data[1].get(0).value.equals(2)) {
            return false;
        }
        if (!m.get(21).equals(2)) {
            return false;
        }
        try {
            m.get(23);
            return false;
        } catch (NoSuchElementException nsee) {
            // expected behavior
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Check correctness of the implementation of the functions size() and remove()
     *
     * @return true if no bugs detected, false otherwise
     */
    public static boolean test3() {
        HashtableMap<Integer, Integer> m = new HashtableMap<>();
        m.put(1, 4);
        if (m.size() != 1)
            return false;
        m.put(4, 5);
        if (m.size() != 2)
            return false;
        m.put(23, 20);
        if (m.size() != 3)
            return false;
        m.put(1, 3);
        if (m.size() != 3)
            return false;
        if (m.remove(40) != null)
            return false;
        if (m.data[1].size() != 1)
            return false;
        Integer removedValue;
        removedValue = m.remove(1);
        if (m.data[1].size() != 0)
            return false;
        if (removedValue == null || !removedValue.equals(4))
            return false;
        removedValue = m.remove(1);
        if (removedValue != null)
            return false;
        if (m.data[4].size() != 1)
            return false;
        removedValue = m.remove(4);
        if (removedValue == null || !removedValue.equals(5))
            return false;
        if (m.data[4].size() != 0)
            return false;
        removedValue = m.remove(23);
        if (removedValue == null || !removedValue.equals(20))
            return false;
        removedValue = m.remove(23);
        if (removedValue != null)
            return false;
        return true;
    }

    /**
     * Check correctness of the implementation of the functions containsKey() and clear()
     *
     * @return true if no bugs detected, false otherwise
     */
    public static boolean test4() {
        HashtableMap<Integer, Integer> m = new HashtableMap<>();
        m.put(3, 20);
        m.put(4, 450);
        m.put(21, 30);
        m.put(37, 12);
        if (m.containsKey(33))
            return false;
        if (!m.containsKey(3) || !m.containsKey(4) || !m.containsKey(21) || !m.containsKey(37))
            return false;
        m.clear();
        for (int i = 0; i < m.data.length; i++) {
            if (m.data[i].size() != 0)
                return false;
        }
        if (m.containsKey(3) || m.containsKey(4) || m.containsKey(21) || m.containsKey(37))
            return false;
        return true;
    }

    /**
     * Check correctness of the implementation of the function doubleCapacity()
     *
     * @return true if no bugs detected, false otherwise
     */
    public static boolean test5() {
        HashtableMap<Integer, Integer> m = new HashtableMap<>(6);
        m.put(3, 20);
        m.put(4, 450);
        m.put(21, 30);
        m.put(37, 100);
        if (m.data.length != 6)
            return false;
        if (!m.data[3].get(0).key.equals(21) || !m.data[3].get(0).value.equals(30))
            return false;
        m.put(80, 100);
        if (m.data.length != 12)
            return false;
        if (!m.data[9].get(0).key.equals(21) || !m.data[9].get(0).value.equals(30))
            return false;
        return true;
    }
}
