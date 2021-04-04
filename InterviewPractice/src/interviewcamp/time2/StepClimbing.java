package interviewcamp.time2;

import java.util.Arrays;

public class StepClimbing {

    public static Integer getWaysToTarget(Integer[] canClimb, Integer target) {
        Integer[] waysToTarget = new Integer[target + 1];
        Arrays.fill(waysToTarget, 0);
        waysToTarget[0] = 1;
        for (int i = 0; i <= target; i++) {
            for(int j = 0; j < canClimb.length; j++) {
                if (i+ canClimb[j] <= target) {
                    waysToTarget[i + canClimb[j]] = waysToTarget[i] + waysToTarget[i + canClimb[j]];
                }
            }
        }

        return waysToTarget[target];
    }

    public static void main(String args[]) {
        System.out.println(getWaysToTarget(new Integer[] {1, 3, 5}, 4) + " should equal 3 (because [1,1,1,1], [1,3,1], [3,1,1])");
    }
}
