package interviewcamp.time1;

import java.util.HashMap;

public class ExponentOptimized {

    public static Long exp(Long base, Long exp, HashMap<Long, Long> memo) {
        if (exp == 1) {
            return base;
        }
        if (memo.containsKey(exp)) {
            return memo.get(exp);
        }
        Long res = exp(base, exp/2, memo) * exp(base, exp/2, memo);
        if (exp % 2 == 1) {
            //The exp is odd - 5/2 will give 2, so we only have 4th power yet.
            res = res * base;
        }
        memo.put(exp, res);
        return res;
    }

    public static void main(String args[]) {
        System.out.println(exp(2l, 10l, new HashMap<>()));
    }
}
