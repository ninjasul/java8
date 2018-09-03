package ninjasul.me.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class StreamEx03 {
    public static void main(String[] args) {
        System.out.println("collect(toList()): " +
        Stream.of(1, 2, 3, 4, 5)
                .filter( i -> i > 2 )
                .map(i -> i * 2)
                .map(i -> "#" + i)
                .collect(toList())
        );

        System.out.println("collect(toSet()): " +
                Stream.of(1, 3, 3, 5, 5)
                        .filter( i -> i > 2 )
                        .map(i -> i * 2)
                        .map(i -> "#" + i)
                        .collect(toSet())
        );

        System.out.println("collect(joining(\", \", \"[\", \"]\")): " +
                Stream.of(1, 3, 3, 5, 5)
                        .filter( i -> i > 2 )
                        .map(i -> i * 2)
                        .map(i -> "#" + i)
                        .collect(joining(", ", "[", "]"))
        );

        System.out.println("distinct().collect(joining(\", \", \"[\", \"]\")): " +
                Stream.of(1, 3, 3, 5, 5)
                        .filter( i -> i > 2 )
                        .map(i -> i * 2)
                        .map(i -> "#" + i)
                        .distinct()
                        .collect(joining(", ", "[", "]"))
        );

        System.out.println("distinct().collect(toList()): " +
                Stream.of(1, 3, 3, 5, 5)
                        .filter( i -> i > 2 )
                        .map(i -> i * 2)
                        .map(i -> "#" + i)
                        .distinct()
                        .collect(toList())
        );

        // i == integer3 구문은 객체 간의 identity 비교 이므로 못 찾아야 할 것처럼 생각이 들지만
        // 실제로 autoboxing 시 Integer.valueOf() 메소드가 호출되어 캐싱이 되므로
        // 3이라는 값을 가진 동일 객체를 비교하는 셈이 됨.
        final Integer integer3 = 3;
        System.out.println(
            Stream.of(1, 2, 3, 4, 5)
                .filter( i -> i == integer3 )
                .findFirst()
        );


        System.out.println(
            Stream.of(1, 2, 3, 4, 5)
            .filter( i -> i.equals(3) )
            .findFirst()
        );

        // Integer.valueOf()의 캐싱 최대 값이 127이므로 아래 코드는 결과 값을 리턴함.
        final Integer integer127 = 127;
        System.out.println(
                Stream.of(1, 2, 3, 4, 5, 127)
                        .filter( i -> i == integer127 )
                        .findFirst()
        );

        // Integer.valueOf()의 캐싱 최대 값이 127이므로 128은 캐싱이 되지 않고 새 객체를 생성함.
        // 따라서 아래 코드는 아무 값도 찾지 못하는 결과를 낳음.
        final Integer integer128 = 128;
        System.out.println(
                Stream.of(1, 2, 3, 4, 5, 128)
                        .filter( i -> i == integer128 )
                        .findFirst()
        );


        System.out.println(
                Stream.of(1, 2, 3, 4, 5, 128)
                        .filter( i -> i.equals(integer128) )
                        .findFirst()
        );

        // i > integer3 와 같이 부등호를 사용하면 부등호는 primitive 전용 연산자이므로
        // unboxing이 일어나고 int 값 비교가 가능함.
        System.out.println(".filter( i -> i > integer3).count(): " +
                Stream.of(1, 2, 3, 4, 5, 128)
                        .filter( i -> i > integer3 )
                        .count()
        );

        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        // External Iterator
        System.out.println("for( Integer i : numbers): ");
        for( Integer i : numbers) {
            System.out.print("i = " + i + " ");
        }

        System.out.println();
        // Internal Iterator
        System.out.println("forEach(i -> System.out.println(i)): ");
        Stream.of(1, 2, 3, 4, 5)
            .forEach(i -> System.out.print(i + " "));
    }
}