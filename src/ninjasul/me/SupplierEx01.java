package ninjasul.me;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class SupplierEx01 {
    public static void main(String[] args) {
        final Supplier<String> helloSupplier = () -> "Hello ";
        System.out.println(helloSupplier.get() + "World");

        long start = System.currentTimeMillis();

        // Supplier를 사용하지 않을 경우 number 값에 관계 없이 getVeryExpensiveValue() 메소드가 무조건 실행되므로 시간 낭비가 발생함.
        printIfValidIndex( 0, getVeryExpensiveValue());
        printIfValidIndex( 1, getVeryExpensiveValue());
        printIfValidIndex( -1, getVeryExpensiveValue());

        System.out.println("It took " + ((System.currentTimeMillis() - start) / 1000) + " seconds");

        start = System.currentTimeMillis();

        // Supplier를 사용하는 경우 LazyEvalution을 수행하므로
        // 조건문을 비교하여 true 인 경우에만 getVeryExpensiveValue() 를 실행하고 false 일 경우에는 실행하지 않음.
        printIfValidIndex( 0, () -> getVeryExpensiveValue());
        printIfValidIndex( 1, () -> getVeryExpensiveValue());
        printIfValidIndex( -1, () -> getVeryExpensiveValue());
        System.out.println("It took " + ((System.currentTimeMillis() - start) / 1000) + " seconds");
    }

    private static String getVeryExpensiveValue() {

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "ninjasul";
    }

    private static void printIfValidIndex( int number, String value ) {
        if( number >= 0 ) {
            System.out.println("The value is " + value + ".");
        }
        else {
            System.out.println("Invalid");
        }
    }

    private static void printIfValidIndex( int number, Supplier<String> value ) {
        if( number >= 0 ) {
            System.out.println("The value is " + value.get() + ".");
        }
        else {
            System.out.println("Invalid");
        }
    }

}