import org.junit.jupiter.api.Assertions;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class CharQueueTest {
    CharQueue empty;
    CharQueue full;
    CharQueue notFull;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        empty = new CharQueue();
        full = new CharQueue();
        full.enqueue('S');
        full.enqueue('m');
        full.enqueue('i');
        full.enqueue('l');
        full.enqueue('e');
        notFull = new CharQueue();
        notFull.enqueue('C');
        notFull.enqueue('l');
        notFull.enqueue('o');
        notFull.enqueue('v');
        notFull.dequeue();
        notFull.dequeue();
    }

    @org.junit.jupiter.api.Test
    void isEmptyTest() {
        assertTrue(empty.isEmpty());
        assertFalse(full.isEmpty());
        assertFalse(notFull.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void sizeTest() {
        assertEquals(0, empty.size());
        assertEquals(5, full.size());
        assertEquals(2, notFull.size());
    }

    @org.junit.jupiter.api.Test
    void clearTest() {
        full.clear();
        assertTrue(full.isEmpty());
        notFull.clear();
        assertTrue(notFull.isEmpty());
        empty.clear();
        assertTrue(empty.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void enqueueTest() {
        empty.enqueue('S');
        assertEquals('S', empty.peek());
        full.enqueue('C');
        char[] expected1 = new char[] {'S', 'm', 'i', 'l', 'e', 'C', '\0', '\0', '\0', '\0'};
        assertArrayEquals(expected1, full.getCircularArray());
        notFull.enqueue('e');
        notFull.enqueue('C');
        notFull.enqueue('l');
        char[] expected2 = new char[] {'C', 'l', 'o', 'v', 'e'};
        assertArrayEquals(expected2, notFull.getCircularArray());
    }

    @org.junit.jupiter.api.Test
    void peekTest() {
        assertEquals('S', full.peek());
        Assertions.assertThrows(NoSuchElementException.class, ()->{
            empty.peek();
        });
        assertEquals('o', notFull.peek());
    }

    @org.junit.jupiter.api.Test
    void dequeueTest() {
        Assertions.assertThrows(NoSuchElementException.class, ()->{
            empty.dequeue();
        });
        assertEquals('S', full.dequeue());
        assertEquals('m', full.peek());
        assertEquals('o', notFull.dequeue());
        assertEquals('v', notFull.peek());
    }
}