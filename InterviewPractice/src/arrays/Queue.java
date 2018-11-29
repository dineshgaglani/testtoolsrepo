package arrays;

public class Queue {

    private Integer[] queue;
    private Integer head = 0;
    private Integer tail = 0;

    public Queue(int capacity) {
        queue = new Integer[capacity];
    }

    public void enqueue(Integer content) throws Exception {
        if (queue[tail] == null) {
            queue[tail] = content;
            tail++;
            tail = tail % queue.length;
        } else {
            throw new Exception("Overflow!!");
        }

    }

    public Integer peek () {
        return queue[head];
    }

    public Integer peekTail() {
        return queue[tail];
    }

    public Integer dequeue() throws Exception {
        if (head == null) {
            throw new Exception("Underflow!");
        } else {
            Integer result = queue[head];
            queue[head] = null;
            head++;
            return result;
        }
    }

    public Integer dequeueFromTail() throws Exception {
        Integer result = queue[tail];
        if(result == null) {
            throw new Exception("Underflow!");
        }
        queue[tail] = null;
        if (tail > 0) {
            tail = tail - 1;
        } else {
            tail = queue.length - 1;
        }
        return result;
    }

    public static void slidingWindow(int a[], int k) {
        //Given an array and a K find the sum of K items and move forward to find the sum of the next K.
        //We can reduce the first item from the sum and add the kth item and then increment i
        int sum = 0;
        for(int i = 0; i < k; i++) {
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
                if(maxQueue.peek() < a[i]) {
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
                } catch(Exception e1) {
                    e.printStackTrace();
                }

            }
        }
        System.out.println(sum);
    }

    public static void main(String args[]) throws Exception {
        slidingWindowWithQueue(new Integer[] {1, 4, 2, 5, 3, 1}, 3);
        maxKElementsInWindow(new Integer[] {4, 6, 5, 2, 4, 7}, 3);
        System.out.println("\n");
        maxKElementsInWindow(new Integer[] {4, 6, 5, 7, 4, 7}, 3);
        System.out.println("\n");
        maxKElementsInWindow(new Integer[] {4, 6, 5, 2, 4, 1, 1}, 3);
    }

}