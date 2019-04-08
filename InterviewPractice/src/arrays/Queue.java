package arrays;

import java.util.Arrays;

/*
    Circular queue with ability to remove elements from both sides.

    Have used a variable size to track elements in the queue, all the issues with not using size are outlined below

    We have a head and a tail starting at 0, when enqueing we add the element to (tail)%length and increment tail by 1,
    tail, when we dequeue we move the head by 1, again (head + 1)%length, the mod length is so that we don't exceed
    the bounds of the size. The array will be full when (end - start) + 1 == length or when end - start == -1, and underflow
    happens when start == end.

    Main questions in mind:
    1. What is the initiation state of head and tail?
    2. Should the element be added to tail spot and then tail incremented or should tail increment and then the element
    be added to tail spot
        a. Adding element to tail and then incrementing tail means that when we are checking for overflow, tail is at the
        spot the element can be added in, and still the code will result in overflow because we check for over flow
        before even adding an element

    //Mistake in strategy: Was setting the value and incrementing tail, but was checking for overflow before setting value.
    The Check was happening before the tail and head would collide resulting in overflow with 1 free spot remaining
    Correct procedure should be
    1. Increment the tail
    2. Check for overflow
    3. Set the value when no overflow

    Problem with overflow state check is it cannot be when tail == head, because that is our intial state

    Solution is to move, check and then push value. So incrementing before inserting means that tail should NOT be
    in the position where the value is about to be inserted, so should NOT be initialized to 0, should be -1. Then during
    insertion we will increment, check for collision with start and then insert, special case when starting off
    because tail and head will be -1.

    For Pop: We check, we move and then we pop. We check before moving, so we should have moved in the wrong position on an earlier pop

    push = move, check, push
    pop = check, move, pop

    check, move, push would've worked in push too if we would end up in the wrong place on the last push
 */
public class Queue {

    private Integer[] queue;
    private Integer head = 0;
    private Integer tail = 0;
    private Integer qs = 0;

    public Queue(int capacity) {
        queue = new Integer[capacity];
    }

    public void enqueue(Integer content) throws Exception {
        if (qs == queue.length) {
            throw new Exception("Overflow!");
        } else {
            queue[tail] = content;
            tail++;
            tail = tail % queue.length;
            qs++;
        }
    }


    public Integer peek() {
        return queue[head];
    }

    public Integer peekTail() {
        return queue[tail];
    }

    public Integer dequeue() throws Exception {
        if (qs == 0) {
            throw new Exception("Underflow!");
        }
        Integer res = queue[head];
        head++;
        head = head % queue.length;
        qs--;
        return res;
    }

    public Integer dequeueFromTail() throws Exception {

        if (qs == 0) {
            throw new Exception("Underflow!");
        }
        Integer res = queue[tail];
        tail--;
        if (tail == -1) {
            tail = queue.length - 1;
        }
        qs--;
        return res;
    }

    public Integer peekAt(Integer index) throws Exception {
        return queue[(head + index)%queue.length];
    }


    public static void slidingWindow(int a[], int k) {
        //Given an array and a K find the sum of K items and move forward to find the sum of the next K.
        //We can reduce the first item from the sum and add the kth item and then increment i
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum = sum + a[i];
        }
        System.out.println(sum);
        for (int i = 0; k < a.length; k++) {
            sum = sum - a[i];
            sum = sum + a[k];
            System.out.println(sum);
            i++;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        int pointer = head;
        for (int i = 0; i < qs; i++) {
            sb.append(queue[pointer] + " ");
            pointer++;
            pointer = pointer % queue.length;
        }
        sb.append("]");
        return sb.toString();
    }

    public static void maxKElementsInWindow(Integer[] a, int k) throws Exception {
        //When we see a value bigger than the first value in the max queue, we dequeue (this removes lowest value)
        //and enqueue current value, this way smaller values keep getting dequeued and bigger values enqueued
        //When we slide away from the max value, we dequeue the max value from the max queue resulting in
        //the second highest value becoming the max value.

        //Principle is that when we encounter a bigger element than the max ahead of the max, we know
        // that the bigger element will be out of the window later than the max, so we can discard it and put max in its place

        //While visiting an element we also check that if the tail is smaller than it, we dequeue until we can insert the current element
        Queue maxQueue = new Queue(k);
        maxQueue.enqueue(a[0]);
        for (int i = 1; i < a.length; i++) {
            try {
                if (maxQueue.peek() < a[i]) {
                    maxQueue.dequeue();
                } else {
                    while (maxQueue.peekTail() < a[i]) {
                        //Can't insert behind this cuz we are smaller, dequeue until we find something bigger
                        //TODO - Test case - dequeue multiple from the tail and then enqueue current
                        maxQueue.dequeueFromTail();
                    }
                }
                maxQueue.enqueue(a[i]);
            } catch (Exception e) {
                //Window has slidden
                if (a[i - k] == maxQueue.peek()) {
                    //TODO - test case - queue is full and element we are passing is not the max
                    maxQueue.dequeue();
                }
            }
            System.out.println(maxQueue.peek());
        }
    }

    //    1, 4, 2, 5, 3, 1
    public static void slidingWindowWithQueue(Integer[] a, int k) {
        Queue q = new Queue(k);
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            try {
                q.enqueue(a[i]);
                sum = sum + a[i];
            } catch (Exception e) {
                try {
                    System.out.println(sum);
                    sum = sum - q.dequeue();
                    i--;
                } catch (Exception e1) {
                    e.printStackTrace();
                }

            }
        }
        System.out.println(sum);
    }

    public static void main(String args[]) throws Exception {
//        slidingWindowWithQueue(new Integer[] {1, 4, 2, 5, 3, 1}, 3);
//        maxKElementsInWindow(new Integer[] {4, 6, 5, 2, 4, 7}, 3);
//        System.out.println("\n");
//        maxKElementsInWindow(new Integer[] {4, 6, 5, 7, 4, 7}, 3);
//        System.out.println("\n");
//        maxKElementsInWindow(new Integer[] {4, 6, 5, 2, 4, 1, 1}, 3);
        Queue q = new Queue(4);
        q.enqueue(1); //1
        System.out.println(q.toString());
        q.enqueue(2); // 1, 2
        System.out.println(q.toString());
        q.enqueue(3); // 1, 2, 3
        System.out.println(q.toString());
        System.out.println(q.peekAt(1)); // 2
        q.dequeue();//2, 3
        System.out.println(q.toString());
        System.out.println(q.peek());//2
        q.enqueue(4);//2, 3, 4
        System.out.println(q.toString());
        q.enqueue(5); //2, 3, 4, 5
        System.out.println(q.toString());
        //        q.enqueue(6);// overflow!
        q.dequeue();
        System.out.println(q.toString());
        q.dequeue();
        System.out.println(q.toString());
        q.dequeue();
        System.out.println(q.toString());
        q.dequeue();
        System.out.println(q.toString());
//        q.dequeue(); //Underflow


    }

}