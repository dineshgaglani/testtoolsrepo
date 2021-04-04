package interviewcamp.time1;

import linkedlist.Stack;

public class MaxStack {

    Stack<Integer> stack;
    Stack<Integer> maxStack;

    public MaxStack() {
        stack = new Stack();
        maxStack = new Stack();
    }

    public Integer getMax( ) {
        return maxStack.peek();
    }

    public void push(Integer elem) {
        stack.push(elem);
        if (maxStack.peek() <= elem) {
            maxStack.push(elem);
        }
    }

    public Integer pop() {
        Integer elem = stack.pop();
        if (maxStack.peek() == elem) {
            maxStack.pop();
        }
        return elem;
    }
}
