package arrays;

/*
    Given an array where value at every index can be considered height of a wall, identify how much water can be stored
    between the walls.

    Approach:
        We iterate the input and when the subsequent char is smaller than the prior, we save the diff in a variable,
        The question to answer is when to take the diff and when to stop taking, the diff is supposed to be taken when
        we find a max until a point, that is the reset point. The next question to ask is what if the max point is the
        first index itself. Then we will need to find the next max. So, we need to find the max from the front and the
        back. Pass the array from the front, get the max value until i. The resulting value at i has to be the diff
        between the max and the actual value at i. The point at which the value in the max increases should be our reset
        point, this approach still doesn't work for the max is the first index scenario. We also need to find the max from
        the back as well, this way we could see if the bigger value is ahead of us we diff with the max before us, and
        if the max is behind us then we diff with the max that is ahead of us.

    Algorithm:
        1. Iterate the array from start saving the max upto i in a new array, call it maxUptoHere
        2. Iterate the array from back saving max from length to i - 1 in a new array, call it maxFromHere
        3. Iterate the array from start, diff the value at i with min(i in maxUptoHere and maxFromHere)
        4. Sum all values in previous array

    Optimized:
        Refer daily coding question 32
 */
public class WaterCollection {

    public int[] getMaxUptoHere (int[] ints) {
        int[] maxes = new int[ints.length];
        maxes[0] = ints[0];
        for (int i = 1; i < ints.length; i++) {
            maxes[i] = Math.max(maxes[i - 1], ints[i]);
        }
        return maxes;
    }

    public int[] getMaxFromHere (int[] ints) {
        int[] maxes = new int[ints.length];
        maxes[maxes.length - 1] = ints[ints.length - 1];
        for (int i = ints.length - 2; i >= 0; i--) {
            maxes[i] = Math.max(maxes[i + 1], ints[i]);
        }
        return maxes;
    }

    public int getWaterCollected (int[] ints) {
        int[] maxUptoHere = getMaxUptoHere(ints);
        int[] maxFromHere = getMaxFromHere(ints);

        int res = 0;
        for (int i = 1; i < ints.length - 1; i++) {
            res = res + (Math.min(maxUptoHere[i], maxFromHere[i]) - ints[i]);
        }

        return res;
    }

    public static void main(String args[]) {
        WaterCollection wc = new WaterCollection();
        System.out.println(wc.getWaterCollected(new int[] {3, 0, 1, 3, 0, 5} ));
    }

}
