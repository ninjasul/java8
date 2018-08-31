package ninjasul.me;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateEx01 {
    public static void main(String[] args) {
        Predicate<Integer> isPositive = i -> i > 0;

        System.out.println(isPositive.test(1));
        System.out.println(isPositive.test(0));
        System.out.println(isPositive.test(-1));

        List<Integer> numbers = Arrays.asList(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5 );
        List<Integer> positiveNumbers = new ArrayList<>();

        for( int curNum : numbers) {
            if( isPositive.test(curNum) ) {
                positiveNumbers.add(curNum);
            }
        }

        System.out.println(positiveNumbers);

        Predicate<Integer> isLessThan3 = i -> i < 3;
        List<Integer> lessThan3Numbers = new ArrayList<>();

        for( int curNum : numbers) {
            if( isLessThan3.test(curNum) ) {
                lessThan3Numbers.add(curNum);
            }
        }

        System.out.println(lessThan3Numbers);

        System.out.println(filter(numbers, isPositive));
        System.out.println(filter(numbers, isLessThan3));
    }

    private static <T> List<T> filter( List<T> list, Predicate<T> predicate ) {
        List<T> filteredList = new ArrayList<>();
        for( T curT : list ) {
            if( predicate.test(curT) ) {
                filteredList.add(curT);
            }
        }

        return filteredList;
    }
}