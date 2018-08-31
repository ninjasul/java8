package ninjasul.me;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Identity {
    public static void main(String[] args) {
        Function<String, Integer> toInt = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.parseInt(s);
            }
        };

        System.out.println(toInt.apply("100"));


        Function<String, Integer> toIntLambda = s -> Integer.parseInt(s);
        System.out.println(toIntLambda.apply("9999"));


        final Function<Integer, Integer> identity = t -> t;
        System.out.println(identity.apply(500));

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(map(numbers, i -> i * 2 ));
        System.out.println(map(numbers, Function.identity()));
    }

    private static <T, R> List<R> map( List<T> list, Function<T, R> mapper ) {
        final List<R> result = new ArrayList<>();

        for( final T t : list ) {
            result.add(mapper.apply(t));
        }

        return result;
    }
}