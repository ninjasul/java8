package ninjasul.me.closure;

public class ClosureEx02 {
    private int number = 999;

    public static void main(String[] args) {
        //test();
        new ClosureEx02().test2();
    }

    /*private static void test() {
        int number = 100;

      *//*  Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(number);
            }
        };
        runnable.run();*//*

        Runnable runnable2 = () -> System.out.println(number);
        runnable2.run();
    }*/

    private void test2() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(number);
            }
        };
        runnable.run();

        Runnable runnable2 = () -> System.out.println(number);
        runnable2.run();
    }
}