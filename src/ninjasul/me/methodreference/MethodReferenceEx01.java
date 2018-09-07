package ninjasul.me.methodreference;

import java.math.BigDecimal;
import java.util.Arrays;

import static java.util.stream.Collectors.toList;

public class MethodReferenceEx01 {
    public static void main(String[] args) {
        Arrays.asList(1, 2, 3, 4, 5)
            //.forEach( i -> System.out.println(i));
            .forEach(System.out::println);

        System.out.println(
            Arrays.asList( new BigDecimal("10.0"), new BigDecimal("23"), new BigDecimal("5"))
                .stream()
                //.sorted( (bd1, bd2) -> bd1.compareTo(bd2))
                //.sorted( BigDecimalUtil::compare )
                .sorted( BigDecimal::compareTo )
                .collect(toList())
        );

        String targetString = "z";
        System.out.println(
            Arrays.asList("a", "b", "c", "d")
                    .stream()
                    //.anyMatch(x -> x.equals("c"))
                    //.anyMatch(targetString::equals)
                    .anyMatch("c"::equals)
        );

    }
}

class BigDecimalUtil {
    public static int compare(BigDecimal bd1, BigDecimal bd2) {
        return bd1.compareTo(bd2);
    }
}