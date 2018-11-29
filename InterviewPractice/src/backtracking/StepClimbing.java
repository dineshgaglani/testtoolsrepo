package backtracking;

import java.util.Arrays;

public class StepClimbing {

    public static void climbSteps(int numSteps, int[] denominations) {
        /*
          How do we get to 1, then how to get to 2 from 1, then 3 from 0 and 2. So the previous steps have recorded the ways to get to the current step and we get the ways to get to the next steps from this step
          If we have 1 and 3 as the denominations then ways to get to 1 is 1, to get to 2 is 1 (1 step from 1) then to get to 3 is 1 step from 2 and another from 0. So when we are at 0, mark 1 in 1 and 3, then when at 1 mark 1 in
           step 2 and then check for step for step 2, from here we can go to 3 and we already have a way to get there, so mark this as 2
         */
        Integer[] steps = new Integer[numSteps + 1];//Including 0th step
        steps[0] = 1;
        for (int i = 1; i < steps.length; i++) {
            steps[i] = 0;
        }
        for(int i = 0; i < steps.length; i++) {
            for (int j = 0; j < denominations.length; j++) {

                if (i + denominations[j] <= numSteps) {
                    steps[i + denominations[j]] =  steps[i + denominations[j]] + steps[i];
                }

            }
        }

        System.out.println(Arrays.asList(steps));
    }

    public static void main (String args[]) {
        climbSteps(3, new int[] {1, 3});
    }
}
