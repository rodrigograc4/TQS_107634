package tqsstack;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TqsStackTest {
    private TqsStack<String> wordsStack;

    @BeforeEach
    public void setUp() {
        wordsStack = new TqsStack<>();
    }

    @Test
    public void testPushAndPop() {
        wordsStack.push("Hello");
        wordsStack.push("World");

        Assertions.assertEquals("World", wordsStack.pop());
        Assertions.assertEquals("Hello", wordsStack.pop());
    }

    @Test
    public void testPeek() {
        wordsStack.push("Hello");
        wordsStack.push("World");

        Assertions.assertEquals("World", wordsStack.peek());
        Assertions.assertEquals(2, wordsStack.size());
    }

    @Test
    public void testIsEmpty() {
        Assertions.assertTrue(wordsStack.isEmpty());

        wordsStack.push("Hello");

        Assertions.assertFalse(wordsStack.isEmpty());
    }

    @Test
    public void testSize() {
        Assertions.assertEquals(0, wordsStack.size());

        wordsStack.push("Hello");
        wordsStack.push("World");

        Assertions.assertEquals(2, wordsStack.size());

        wordsStack.pop();

        Assertions.assertEquals(1, wordsStack.size());
    }

    @AfterEach
    public void tearDown() {
        wordsStack = null;
    }
}
