package interviewcamp.time1;

import java.util.HashMap;

public class Exponent {

    public static Long exp(Long base, Long ex, HashMap<Long, Long> memo) {
        if (ex == 1) {
            return base;
        }
        Long res = 0l;
        if (memo.containsKey(ex)) {
            res = memo.get(ex);
        } else {
           res = base * exp(base, ex - 1, memo);
           memo.put(ex, res);
        }

        return res;
    }
}
