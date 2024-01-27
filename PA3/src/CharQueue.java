/*
    Name: Zixun Zhang
    PID:  A16985661
 */

import java.util.NoSuchElementException;

/**
 * Character Queue Implementation
 * Implements a circular queue that stores characters only.
 * Uses an array as the underlying data structure,
 * where front and rear indices will be managed using a circular approach.
 *
 * @author Zixun Zhang
 * @since Jan. 26, 2024
 */
public class CharQueue {
    /* instance variables, feel free to add more if you need */
    private char[] circularArray;
    private int length;
    private int front;
    private int rear;

    private static final int INITIAL_CAPACITY = 5;
    private static final int DOUBLE = 2;

    /**
     * Creates a new queue with an initial capacity of 5.
     */
    public CharQueue() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Creates a new queue with the specified capacity.
     *
     * @param capacity the number of elements the CharQueue can hold.
     */
    public CharQueue(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Argument is out of valid range.");
        }
        circularArray = new char[capacity];
        length = capacity;
        front = 0;
        rear = 0;
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if it is empty, false otherwise.
     */
    public boolean isEmpty() {
        if (circularArray[front] == '\0') {
            return true;
        }
        return false;
    }

    /**
     * Returns the number of elements currently stored in the queue.
     *
     * @return the number of elements currently stored in the queue.
     */
    public int size() {
        if (!isEmpty()) {
            if (rear > front) {
                return rear - front;
            }
            else {
                return length - front + rear;
            }
        }
        return 0;
    }

    /**
     * Clears all elements in the queue.
     */
    public void clear() {
        circularArray = new char[length];
        front = rear;
    }

    /**
     * Adds a new elem to the back of the queue.
     * Double the capacity of the queue when adding the elem to a full queue.
     *
     * @param elem the element needed to add to the queue.
     */
    public void enqueue(char elem) {
        if (size() == length) {
            char[] newCircularArr = new char[length * DOUBLE];
            for (int i = front; i < length; i++) {
                newCircularArr[i - front] = circularArray[i];
            }
            for (int j = 0; j < rear; j++) {
                newCircularArr[length - front + j] = circularArray[j];
            }
            circularArray = newCircularArr;
            front = 0;
            rear = length;
            length = length * DOUBLE;
            circularArray[rear] = elem;
            rear++;
        }
        else {
            circularArray[rear] = elem;
            rear++;
            if (rear == length) {
                rear = 0;
            }
        }
    }

    /**
     * Returns the element at the front of the queue.
     *
     * @return the element at the front of the queue.
     * @throws NoSuchElementException if the queue is empty.
     */
    public char peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("The queue is empty.");
        }
        return circularArray[front];
    }

    /**
     * Returns and removes the element at the front of the queue.
     *
     * @return the element at the front of the queue.
     * @throws NoSuchElementException if the queue is empty.
     */
    public char dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("The queue is empty.");
        }
        char elem = circularArray[front];
        front++;
        if (front == length) {
            front = 0;
        }
        return elem;
    }

    /**
     * The getter method to gets the queue.
     *
     * @return the character queue.
     */
    public char[] getCircularArray() {
        return circularArray;
    }
}
