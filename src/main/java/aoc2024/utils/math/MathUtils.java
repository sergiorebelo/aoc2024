package aoc2024.utils.math;

public class MathUtils {

    public enum LongsOperation {
        ADD { public long apply(long a, long b) { return a + b; } },
        MULTIPLY { public long apply(long a, long b) {  return a * b; }  },
        CONCATENATE {  public long apply(long a, long b) {   return Long.parseLong("" + a + b);   }  };
        public abstract long apply(long a, long b);
    }
}
