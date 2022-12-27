package rtc.utils;

/**
 * Help to do test.
 */
public class Test {
    static private int sampleNums = 0;
    static private int pass = 0;

    static public void equalInt(int expect, int actual) {
        sampleNums++;
        if (expect == actual) {
            pass++;
        } else {
            var loc = Thread.currentThread().getStackTrace()[2];
            var errInfo = String.format(
                    "at %s.%s(%s:%s) expect: %d actual: %d\n",
                    loc.getClassName(),
                    loc.getMethodName(),
                    loc.getFileName(),
                    loc.getLineNumber(),
                    expect,
                    actual);
            System.err.println(errInfo);
        }
    }

    static public void equalDouble(double expect, double actual) {
        sampleNums++;
        if (Double.doubleToLongBits(expect) == Double.doubleToLongBits(actual)) {
            pass++;
        } else {
            var loc = Thread.currentThread().getStackTrace()[2];
            var errInfo = String.format(
                    "at %s.%s(%s:%s) expect: %f actual: %f\n",
                    loc.getClassName(),
                    loc.getMethodName(),
                    loc.getFileName(),
                    loc.getLineNumber(),
                    expect,
                    actual);
            System.err.println(errInfo);
        }
    }

    static public void over() {
        System.out.println("------------");
        System.out.printf("%d/%d passed.%n", pass, sampleNums);
    }
}
