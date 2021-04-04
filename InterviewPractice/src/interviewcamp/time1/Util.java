package interviewcamp.time1;

public class Util<T> {

    public void swap(T[] arr, int from, int to) {
        T temp = arr[from];
        arr[from] = arr[to];
        arr[to] = temp;
    }
}
