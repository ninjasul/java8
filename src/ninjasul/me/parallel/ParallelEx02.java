package ninjasul.me.parallel;

import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelEx02 {

    public static long iterativeSum( long n ) {
        long result = 0;
        for( long i = 0; i <= n; ++i ) {
            result += i;
        }
        return result;
    }

    public static long sequentialSum( long n ) {
        return Stream.iterate( 1L, i -> i + 1 ).limit(n).reduce(Long::sum).get();
    }

    public static long parallelSum( long n ) {
        return Stream.iterate( 1L, i -> i + 1 ).limit(n).parallel().reduce(Long::sum).get();
    }

    public static long rangedSum( long n ) {
        return LongStream.rangeClosed( 1, n ).reduce(Long::sum).getAsLong();
    }

    public static long parallelRangedSum( long n ) {
        return LongStream.rangeClosed(1, n).parallel().reduce(Long::sum).getAsLong();
    }

    private static void slowDown() {
        try {
            TimeUnit.MILLISECONDS.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static long iterativeSlowSum( long n ) {
        long result = 0;
        for( long i = 0; i <= n; ++i ) {
            result += i;
            slowDown();
        }
        return result;
    }

    public static long sequentialSlowSum( long n ) {
        return Stream.iterate( 1L, i -> i + 1 ).limit(n).peek( i -> slowDown() ).reduce(Long::sum).get();
    }

    public static long parallelSlowSum( long n ) {
        return Stream.iterate( 1L, i -> i + 1 ).limit(n).parallel().peek( i -> slowDown() ).reduce(Long::sum).get();
    }

    public static long rangedSlowSum( long n ) {
        return LongStream.rangeClosed( 1, n ).peek( i -> slowDown() ).reduce(Long::sum).getAsLong();
    }

    public static long parallelRangedSlowSum( long n ) {
        return LongStream.rangeClosed(1, n).parallel().peek( i -> slowDown() ).reduce(Long::sum).getAsLong();
    }
    public static void main(String[] args) {
        final long n = 1000;

        // 가우스 방법(단순연산에서 알고리즘이 좋으므로 속도가 엄청나다. 따라잡을수가 없음)
        final long start = System.currentTimeMillis();
        System.out.println((1 + n) * (n / 2));
        System.out.println((System.currentTimeMillis() - start) + " ms\n");


        /*final long start1 = System.currentTimeMillis();
        System.out.println("    iterativeSum(n): " + iterativeSum(n));
        System.out.println("                     " + (System.currentTimeMillis() - start1) + " ms\n");

        final long start2 = System.currentTimeMillis();
        System.out.println("    sequentialSum(n): " + sequentialSum(n));
        System.out.println("                     " + (System.currentTimeMillis() - start2) + " ms\n");

        final long start3 = System.currentTimeMillis();
        System.out.println("      parallelSum(n): " + parallelSum(n));
        System.out.println("                     " + (System.currentTimeMillis() - start3) + " ms\n");

        final long start4 = System.currentTimeMillis();
        System.out.println("        rangedSum(n): " + rangedSum(n));
        System.out.println("                     " + (System.currentTimeMillis() - start4) + " ms\n");

        final long start5 = System.currentTimeMillis();
        System.out.println("parallelRangedSum(n): " + parallelRangedSum(n));
        System.out.println("                     " + (System.currentTimeMillis() - start5) + " ms\n");*/

        final long start6 = System.currentTimeMillis();
        System.out.println("    iterativeSlowSum(n): " + iterativeSlowSum(n));
        System.out.println("                     " + (System.currentTimeMillis() - start6) + " ms\n");

        final long start7 = System.currentTimeMillis();
        System.out.println("    sequentialSlowSum(n): " + sequentialSlowSum(n));
        System.out.println("                     " + (System.currentTimeMillis() - start7) + " ms\n");

        final long start8 = System.currentTimeMillis();
        System.out.println("      parallelSlowSum(n): " + parallelSlowSum(n));
        System.out.println("                     " + (System.currentTimeMillis() - start8) + " ms\n");

        final long start9 = System.currentTimeMillis();
        System.out.println("        rangedSlowSum(n): " + rangedSlowSum(n));
        System.out.println("                     " + (System.currentTimeMillis() - start9) + " ms\n");

        final long start10 = System.currentTimeMillis();
        System.out.println("parallelRangedSlowSum(n): " + parallelRangedSlowSum(n));
        System.out.println("                     " + (System.currentTimeMillis() - start10) + " ms\n");
    }
}