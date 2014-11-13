package com.stacks;

import com.singlelist.LinkedList.*;

/**
 * Created by dgaglani on 5/6/14.
 */
public class Driver {

    public static void main(String args[]) {
        Stack stack = new LinkedListStack<Node>();
        try{
            stack.push(new Node(1));
            stack.push(new Node(2));
            stack.push(new Node(3));
            stack.push(new Node(4));
            stack.push(new Node(5));
            System.out.print("Peeking: \t");
            System.out.print(((Node)stack.peek()).value);
            System.out.print("\n Stack size: \t");
            System.out.print(stack.stackSize());
            System.out.print("\n Stack contents: ");
            stack.displayStackContents();
            //System.out.print("\n Dynamic array Size: ");
            //System.out.print(((DynamicArrayStack)stack).dynamicArraySize());

            System.out.print("\n Popping and peeking: \t");
            stack.pop();
            System.out.print(((Node)stack.peek()).value);
            System.out.print("\n Stack size: \t");
            System.out.print(stack.stackSize());
            System.out.print("\n Stack contents: ");
            stack.displayStackContents();


            System.out.print("\n Again popping and peeking: \t");
            stack.pop();
            System.out.print(((Node)stack.peek()).value);
            System.out.print("\n Stack size: \t");
            System.out.print(stack.stackSize());
            System.out.print("\n Stack contents: ");
            stack.displayStackContents();

            System.out.println("Now pushing and peeking: \t");
            stack.push(new Node(5));
            System.out.print(((Node)stack.peek()).value);
            System.out.print("\n Stack size: \t");
            System.out.print(stack.stackSize());
            System.out.print("\n Stack contents: ");
            stack.displayStackContents();

            System.out.println("Stack reverse: \t");
            ((LinkedListStack)stack).reverseStack();
            System.out.print("\n Stack size: \t");
            System.out.print(stack.stackSize());
            System.out.print("\n Stack contents: ");
            stack.displayStackContents();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
