import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.math.BigInteger;
import java.util.ArrayList;

public class MonoFluxTest {
    @Test
    public void anyTest() {
        System.out.println("FluxTest.anyTest");
        Flux.just(3, 5, 7, 9, 11)
                .any(v -> v % 2 == 0)
                .subscribe(v -> System.out.println("v = " + v));
    }

    @Test
    public void reduceTest() {
        System.out.println("FluxTest.reduceTest");
        Flux.range(1, 10)
                .reduce(10, (acc, v) -> acc + v)
                .subscribe(System.out::println);
    }

    @Test
    public void scanTest() {
        System.out.println("FluxTest.scanTest");
        Flux.range(100, 5)
                .scan(0, (acc, v) -> acc + v)
                .subscribe(System.out::println);
    }

    @Test
    public void concatTest() {
        System.out.println("FluxTest.concatTest");
        Flux.concat(
                Flux.range(1, 3),
                Flux.range(11, 3),
                Flux.range(101, 3)
        ).subscribe(System.out::println);
    }

    @Test
    public void bufferTest() {
        System.out.println("FluxTest.bufferTest");
        Flux.range(20, 10)
                .buffer(3)
                .subscribe(System.out::println);
    }

    @Test
    public void windowTest() {
        System.out.println("FluxTest.windowTest");
        Flux<Flux<Integer>> winFlux = Flux.range(100, 100).windowUntil(this::isPrime, false);
        winFlux.subscribe(window -> window.collectList().subscribe(System.out::println));
    }

    private boolean isPrime(Integer num) {
//        return true;
        return (new BigInteger(String.valueOf(num))).isProbablePrime(100);
    }

    @Test
    public void groupbyTest() {
        System.out.println("FluxTest.groupbyTest");
        Flux.range(10, 10)
                .groupBy(v -> v % 2 == 0 ? "Even" : "Odd")
                .subscribe(groupedFlux ->
                        groupedFlux.reduce(new ArrayList<>(), (list, v) -> {
                                    list.add(v);
                                    return list;
                                }).filter(list -> !list.isEmpty())
                                .subscribe(v -> System.out.println("* key : " + groupedFlux.key() + "\n* value : " + v + "\n"))
                );
    }
}