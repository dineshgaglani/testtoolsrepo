package amazon;

/*
A university has exactly one turnstile. It can be used either as an exit or an entrance. Unfortunately, sometimes many people want to pass through the turnstile and their directions can be different. The ith person comes to the turnstile at time[i] and wants to either exit the university if direction[i] = 1 or enter the university if direction[i] = 0. People form 2 queues, one to exit and one to enter. They are ordered by the time when they came to the turnstile and, if the times are equal, by their indices.

If some person wants to enter the university and another person wants to leave the university at the same moment, there are three cases:
If in the previous second the turnstile was not used (maybe it was used before, but not at the previous second), then the person who wants to leave goes first.
If in the previous second the turnstile was used as an exit, then the person who wants to leave goes first.
If in the previous second the turnstile was used as an entrance, then the person who wants to enter goes first.
Passing through the turnstile takes 1 second.

For each person, find the time when they will pass through the turnstile.

Input
arrTime, an array of n integers where the value at index i is the time in seconds when the ith person will come
direction, a list of integers where the value at indexi is the direction of the ith person.

Output
an array of integers where the value at index i is the time when the ith person will pass the turnstile.

Constraints
1 <= n <= 10^5
0 <= arrTime[i] <= 10^9 for 0<= i <=n-1
arrTime[i] <= arrTime[i+1] for 0 <= i <= n - 2
0 <= direction[i] <= 1 for 0 <= o <=  n - 1

Example1
Input:
n = 4
arrTime = [1, 1, 2, 6]
direction = [0, 1, 1, 0]

Output:
[3,1,2,6]

Explanation:
At time 1, person 0 and 1 want to pass through the turnstile. Person 0 wants to enter the store and person 1 wants to leave the store. The turnstile was not used in the previous second, so the priority is on the side of the person 1
At time 2, person 0 and 2 want to pass through the turnstile. Person 2 wants to leave the store and at the previous second the turnstile was used as an exit, so the person 2 passes through the turnstile.
At time 3, person 0 passes through the turnstile.
At time 6, person 3 passes through the turnstile.

Example2
Input:
numPersons = 5
arrTime = [1,2,2,4,4]
direction = [0, 1, 0, 0, 1]

Output:
[1, 3, 2, 5, 4]

Explanation:
At time 1, person 0 passes through the turnstile (enters).
At time 2, persons 1 (exit) and 2 (enter) want to pass through the turnstile, and person 2 passes through the turnstile because their direction is equal to the direction at the previous second.
At time 3. person 1 passes through the turnstile (exit).
At time 4, persons 3 (enter) and 4 (exit) want to pass through the turnstile. Person 4 passes through the turnstile because at the previous second the turnstile was used to exit.
At time 5, person 3 passes through the turnstile.
 */
public class Turnstile {

    public static Long[] getTimes(Long[] aT, Integer[] dT) {
        //Todo
        return null;
    }

    public static void main (String args[]) {

    }
}
