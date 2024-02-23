/*** JUnit imports ***/
// We will use the BeforeEach and Test annotation types to mark methods in
// our test class.
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
// The Assertions class that we import from here includes assertion methods like assertEquals()
// which we will used in test1000Inserts().
import static org.junit.jupiter.api.Assertions.assertEquals;
// More details on each of the imported elements can be found here:
// https://junit.org/junit5/docs/current/api/org.junit.jupiter.api/org/junit/jupiter/api/package-summary.html
/*** JUnit imports end  ***/

public class TestMyList {

    protected ListADT<Integer> _instance = null;

    //BeforeEach annotation makes a method invocked automatically
    //before each test
    @BeforeEach
    public void createInstane() {
        _instance = new MyList<Integer>();
    }

    //The @Test annotation allows JUnit to recognize its following
    //method as a test method
    @Test
    public void test1000Inserts() {
	// This tests inserts 1000 integers into the list and then
	// checks if they're in the list at the expected index
        for (int i = 0; i < 1000; i++) {
            _instance.add(i);
	    }
	    for (int i = 0; i < 1000; i++) {
            assertEquals(i, _instance.get(i));
        }
    }

    @Test
    public void testInsertSize() {
	// method of ListADT returns the correct result after inserting each one of them into the list.
        _instance.add(2);
        _instance.add(3);
        _instance.add(2);
        _instance.add(10);
        _instance.add(12);
        _instance.add(13);
        _instance.add(31);
        _instance.add(11398);
        _instance.add(123);
        _instance.add(4398);
        assertEquals(10, _instance.size());
    }

    @Test
    public void testRemoveSize() {
        // 1) inserts 10 integers into the list
        // 2) after all insertions are done, removes them
        // and checks after each remove if the .size() method of ListADT returns the correct result.
        _instance.add(2);
        _instance.add(3);
        _instance.add(2);
        _instance.add(10);
        _instance.add(12);
        _instance.add(13);
        _instance.add(31);
        _instance.add(11398);
        _instance.add(123);
        _instance.add(4398);
        for (int i = 0; i < 10; i++) {
            _instance.remove(0);
        }
        assertEquals(0, _instance.size());
    }

}
