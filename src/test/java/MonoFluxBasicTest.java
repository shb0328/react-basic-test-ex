import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MonoFluxBasicTest {
    private List<String> list;
    private List<String> result;

    @BeforeEach
    public void before() {
        result = new ArrayList<>();
        list = new ArrayList<>();
        list.add("hello,");
        list.add("this");
        list.add("is");
    }

    @AfterEach
    public void after() {
        System.out.println("list = " + list.stream().collect(Collectors.joining(" ")));

        if(result.isEmpty()) {
            System.out.println("result is empty!!");
            return;
        } else {
            System.out.println("result = " + result);
            System.out.println("result = " + result.stream().collect(Collectors.joining(" ")));
        }
    }

    @Test
    public void test1() {
        Mono<List<String>> mono = Mono.just(list);
        mono.map(data -> {
            result = data.stream().collect(Collectors.toList());
            return result;
        }).map(result -> {
            result.add("Mono");
            return result;
        });
    }

    @Test
    public void test2() {
        Mono<List<String>> mono = Mono.just(list);
        mono.map(data -> {
            result = data.stream().collect(Collectors.toList());
            return result;
        }).map(result -> {
            result.add("Mono");
            return result;
        }).subscribe();
    }

    @Test
    public void test3() {
        Mono<List<String>> mono = Mono.just(list);
        Flux<String> flux = mono.flatMapMany(Flux::fromIterable);
        flux.map(v -> {
            result.add(v);
            return result;
        });
    }

    @Test
    public void test4() {
        Mono<List<String>> mono = Mono.just(list);
        Flux<String> flux = mono.flatMapMany(Flux::fromIterable);
        flux.map(v -> {
            result.add(v);
            return result;
        }).subscribe();
    }

    @Test
    public void test5() {
        Mono<List<String>> mono = Mono.just(list);
        Flux<String> flux = mono.flatMapMany(Flux::fromIterable);
        flux.map(v -> {
            result.add(v);
            return result;
        }).map(v -> {
            v.add("Flux!");
            return v;
        }).subscribe();
    }

    @Test
    public void test6() {
        Mono<List<String>> mono = Mono.just(list);
        Flux<String> flux = mono.flatMapMany(Flux::fromIterable);
        flux.map(v -> {
            result.add(v);
            return v;
        }).subscribe();

        result.add("Flux!!");
    }
}
