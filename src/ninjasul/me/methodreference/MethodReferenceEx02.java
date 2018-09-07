package ninjasul.me.methodreference;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class MethodReferenceEx02 {
    public static void main(String[] args) {
        methodReference03();
    }

    private static void methodReference03() {
        /* First Class Function */

        /*
        * Function can be passed as a parameter to another function.
        */
        System.out.println(testFirstClassFunction1(3, i -> String.valueOf( i  * 2 )));
        System.out.println(testFirstClassFunction1(3, MethodReferenceEx02::doubleThenToString ));

        System.out.println("\n-------------------------------------------------------------------");

        /*
         * Using Lambda Expression
         */
        Function<Integer, String> fl = getDoubleThenToStringUsingLambdaExpression();
        final String resultFormFl = fl.apply(3);
        System.out.println(resultFormFl);

        /*
         * Using Method Reference
         */
        final Function<Integer, String> fmr = getDoubleThenToStringUsingMethodReference();
        final String resultFormFmr = fmr.apply(3);
        System.out.println(resultFormFmr);

        /*
         * A function can be stored in the data structure.
         */

        System.out.println("\n-------------------------------------------------------------------");

        /*
         * Using Lambda Expression
         */
        final List<Function<Integer, String>> fsL = Arrays.asList(i -> String.valueOf( i * 2 ));
        for (Function<Integer, String> f : fsL) {
            final String result = f.apply(3);
            System.out.println(result);
        }

        /*
         * Using Method Reference
         */
        final List<Function<Integer, String>> fsMr = Arrays.asList(MethodReferenceEx02::doubleThenToString);
        for (Function<Integer, String> f : fsMr) {
            final String result = f.apply(3);
            System.out.println(result);
        }

        System.out.println("\n-------------------------------------------------------------------");

        /*
         * Using Lambda Expression
         */
        final Function<Integer, String> fl2 = i -> String.valueOf( i * 2 );
        final String resultFl2 = fl2.apply(5);
        System.out.println(resultFl2);


        /*
         * Using Method Reference
         */
        final Function<Integer, String> fmr2 = MethodReferenceEx02::doubleThenToString;
        final String resultFmr = fmr2.apply(5);
        System.out.println(resultFmr);

        System.out.println("\n-------------------------------------------------------------------");
        /*
         * Using Method Reference
         */
        final List<Function<Integer, String>> fsBoth = Arrays.asList(i -> String.valueOf(i * 2), MethodReferenceEx02::doubleThenToString, MethodReferenceEx02::addHashPrefix);
        for (Function<Integer, String> f : fsBoth) {
            final String result = f.apply(7);
            System.out.println(result);
        }


    }


    private static String doubleThenToString( int i ) {
        return String.valueOf( i * 2 );
    }

    private static String testFirstClassFunction1( int n, Function<Integer, String> f ) {
        return "The result is " + f.apply(n);
    }

    private static Function<Integer, String> getDoubleThenToStringUsingLambdaExpression() {
        return i -> String.valueOf( i * 2 );
    }

    private static Function<Integer, String> getDoubleThenToStringUsingMethodReference() {
        return MethodReferenceEx02::doubleThenToString;
    }

    private static String addHashPrefix( int number ) {
        return "#" + number;
    }
}
