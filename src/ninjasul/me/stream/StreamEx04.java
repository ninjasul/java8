package ninjasul.me.stream;

import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StreamEx04 {
    public static void main(String[] args) {
        System.out.println(
            Stream.of(1, 2, 3, 4, 5)
                .collect(toList())
        );

        System.out.println(
                Stream.of(1, 2, 3, 4, 5)
                        .collect(toSet())
        );

        System.out.println(
                Stream.of(1, 2, 3, 4, 5)
                        .map( i -> String.valueOf(i))
                        .collect(joining())
        );

    }
}