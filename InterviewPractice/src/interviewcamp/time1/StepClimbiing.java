package interviewcamp.time1;

import java.util.Arrays;

/*
Question:ClimbingStepsProblem. Let’s say you have to climb N steps. You can jump 1 step, 3 steps or 5 steps at a time.
How many ways are there to get to the top of the steps.
 */
public class StepClimbiing {

    public static Integer getWaysToStair(Integer[] canClimb, Integer target) {
        Integer[] waysToClimb = new Integer[target + 1];
        Arrays.fill(waysToClimb, 0);
        waysToClimb[0] = 1;
        for(int i = 0; i <= target; i++) {
            for (int j = 0; j < canClimb.length; j++) {
                if (i + canClimb[j] <= target) {
                    //MISTAKE
                    //waysToClimb[i + canClimb[j]] = waysToClimb[i] + waysToClimb[canClimb[j]];
                    //END MISTAKE

                    waysToClimb[i + canClimb[j]] = waysToClimb[i + canClimb[j]] + waysToClimb[i];
                }
            }
        }

        return waysToClimb[target];
    }

    public static void main (String args[]) {
        System.out.println(getWaysToStair(new Integer[] {1, 3, 5}, 4) + " should equal : 3");
    }
}
