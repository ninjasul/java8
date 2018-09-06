package ninjasul.me.closure;

public class ClosureEx01 {
    private int number = 999;

    public static void main(String[] args) {
        //new ClosureEx01().test1();
        //new ClosureEx01().test2();
        new ClosureEx01().test5();
    }

    @Override
    public String toString() {
        return new StringBuilder("ClosureEx01{")
                .append("number=")
                .append(number)
                .append('}')
                .toString();
    }

    public static <T> String toString( int number, T value ) {
        return "[" + number + "] The value is " + String.valueOf(value);
    }

    private void test1() {
        // java8 이하에는 final 변수여야만 closure 에서 접근이 가능했으나
        // java8 이상부터는 final이 아니어도 접근이 가능함.
        // 하지만 final 키워드를 붙이지 않았을 뿐이지 실질적으로는 final이나 마찬가지임.
        // 즉, closure 안이든 밖이든 해당 변수 값을 변경하려고 하면 오류가 발생함.
        // 이유는 free variable 값을 변경할 수 있도록 허용하면 closure 실행 시 경합이 발생할 수 있으므로 이를 방지 하기 위해서임.
        int number = 100;

        // closure 밖에서 변경하더라도 컴파일 오류 발생. 심지어는 closure 코드 이후에 변경해도 마찬가지임.
        //number = 1;

        testClosure("Anonymous Class", new Runnable() {
            @Override
            public void run() {
                // final 키워드만 안붙었을 뿐이지 아래 처럼 변수 값을 바꾸려고 하면 컴파일 오류가 발생함.
                //number = 50;
                System.out.println(number);
            }
        });

        // closure 코드 이후에 free variable을 변경해도 컴파일 오류 발생.
        //number = 6;

        testClosure("Lambda Expression", () -> System.out.println(number));
    }

    private void test2() {

        int number = 100;

        // 기존 형태의 run() 메소드는 클래스명.this.멤버변수명 으로 접근이 가능함.
        testClosure("Anonymous Class", new Runnable() {
            @Override
            public void run() {
                System.out.println(ClosureEx01.this.number);

                // this.number는 Runnable 객체에 대한 this 를 뜻하므로 number에 접근할 수 없음.
                //System.out.println(this.number);
            }
        });

        // Lambda 의 경우에는 this.멤버변수명 만으로도 접근이 가능함. 자체 scope이 없다는 의미임. 진정한 의미의 closure 임.
        testClosure("Lambda Expression", () -> System.out.println(this.number));
    }

    private void test3() {

        int number = 100;

        // Runnable 객체의 toString() 이 호출되므로 Object.toString() 이 호출됨.
        testClosure("Anonymous Class", new Runnable() {
            @Override
            public void run() {
                System.out.println("this.toString(): " + this.toString());
            }
        });

        // ClosureEx01 객체의 toString() 이 호출됨.
        testClosure("Anonymous Class", new Runnable() {
            @Override
            public void run() {
                System.out.println("ClosureEx01.this.toString(): " +  ClosureEx01.this.toString());
            }
        });

        testClosure("Lambda Expression", () -> System.out.println("this.toString(): " + this.toString()));
    }

    private void test4() {

        System.out.println("\"ClosureEx01 calling toString()\": " + toString());
        System.out.println("\"ClosureEx01 calling toString(int, String)\": " + toString(1, "Hello"));

        testClosure("Anonymous Class", new Runnable() {
            @Override
            public void run() {
                System.out.println("toString(int, String) causes compile-time error ");
                System.out.println("ClosureEx01.this.toString(int, String): " + ClosureEx01.this.toString(1, "Test"));
            }
        });

        testClosure("Lambda Expression", () -> System.out.println("toString(int, String): " + toString(1, "Test")));
    }

    private void test5() {

        int number = 100;

        testClosure("Anonymous Class", new Runnable() {
            @Override
            public void run() {
                /*****************************************************************************************
                 * 익명클래스의 경우 아래와 같이 number 를 재 선언 해도 컴파일 오류가 발생하지 않으므로
                 * 나중에 오류를 발생시킬 가능성이 있음. 주의 해야 함.
                 ****************************************************************************************/
                int number = 50;        // no-compile error
                System.out.println(number);
            }
        });

        testClosure("Lambda Expression", () -> {
            /*****************************************************************************************
             * lambda expression 은 scope 이 메소드 내부에 국한 되는 것이 아니라 클래스 전체임.
             * 따라서 int number = 50; 과 같이 재선언을 하면 다음과 같은 컴파일 오류가 발생함.
             * Variable 'number' is already defined in the scope
             ****************************************************************************************/
            // int number = 50;         // compile error
            System.out.println(number);
        });
    }

    private static void testClosure(final String name, Runnable runnable) {
        System.out.println("=========================================================================");
        System.out.println("Start " + name + ": " );
        runnable.run();
        System.out.println("=========================================================================");
    }
}