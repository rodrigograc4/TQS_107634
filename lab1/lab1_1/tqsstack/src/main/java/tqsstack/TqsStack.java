package tqsstack;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class TqsStack<T> {
    private List<T> stack;

    public TqsStack() {
        this.stack = new ArrayList<>();
    }

    // add an item on the top
    public void push(T item) {
        stack.add(item);
    }

    // remove the item at the top
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.remove(stack.size() - 1);
    }

    // return the item at the top (without removing it)
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.get(stack.size() - 1);
    }

    // return the number of items in the stack
    public int size() {
        return stack.size();
    }

    // return whether the stack has no item
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    // main method to test the stack
    public static void main(String[] args) {
        TqsStack<Integer> stack = new TqsStack<>();
        stack.push(23);
        stack.push(12);
        stack.push(37);

        System.out.println("Stack size: " + stack.size());
        System.out.println("Top element: " + stack.peek());

        while (!stack.isEmpty()) {
            System.out.println("Popped element: " + stack.pop());
            System.out.println("Stack size: " + stack.size());
            if (!stack.isEmpty())
                System.out.println("Top element: " + stack.peek());
        }
    }
}
