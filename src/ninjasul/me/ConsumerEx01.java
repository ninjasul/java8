package ninjasul.me;

import java.util.function.Consumer;

public class ConsumerEx01 {
    public static void main(String[] args) {
        final Consumer<String> print = (value) -> System.out.println(value);
        final Consumer<String> greetings = value -> System.out.println("Hello " + value);

        print.accept("Hi");
        greetings.accept("World");
        greetings.accept("ninjasul");
    }
}