package lambda;

@FunctionalInterface
public interface MyFunctionalInterface {
    String myAbFunction(int num);
//    String f();
    default void printDefault() {
        System.out.println("default method");
    }
    default void printDefault2() {
        System.out.println("default method2");
    }
    static void printStatic() {
        System.out.println("this is static method!");
    }
    static void printStatic2() {
        System.out.println("this is static method2!");
    }
}