import lambda.MyFunctionalInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FunctionalInterfaceTest {

    private static MyFunctionalInterface f1;
    private static MyFunctionalInterface f2;
    private static MyFunctionalInterface f3;

    @BeforeEach
    public void before() {
        f1 = num -> String.valueOf(num);
        f2 = num -> "num : "+ num;
        f3 = num -> String.valueOf(num + 100);
    }

    @Test
    public void test1() {
        System.out.println(f1.myAbFunction(3));
        System.out.println(f2.myAbFunction(30));
        System.out.println(f3.myAbFunction(300));
    }

    @Test
    public void test2() {
        myPrint(f1);
        myPrint(n -> String.valueOf(n));
        myPrint(f2);
        myPrint(f3);
    }

    private static void myPrint(MyFunctionalInterface f) {
        System.out.println(f.myAbFunction(100));
    }
}
