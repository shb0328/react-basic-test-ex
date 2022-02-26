package pubsub;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class ReactiveApp {
    private List<String> list = new ArrayList<>();

    public void mono1(Publisher<String> publisher) {
        System.out.println("***** App.mono1 *****");
//        Mono<String> from = Mono.from(publisher);
        Mono.from(publisher).map(v -> {
            System.out.println("v = " + v);
            return v.length();
        }).subscribe(v -> System.out.println("* Mono : " + v));
    }

    public void mono2() {
        System.out.println("***** App.mono2 *****");
        Mono.just("just mono data").map(v -> {
            System.out.println("v = " + v);
            return v;
        }).subscribe(v -> list.add(v));
        System.out.println("* list : " + list.toString());
    }

    public void flux1(Publisher<String> publisher) {
        System.out.println("***** App.flux1 *****");
        Flux.from(publisher).map(v -> {
            System.out.println("v = " + v);
            return v;
        }).subscribe();
    }

    public void flux2() {
        System.out.println("***** App.flux2 *****");
        Flux.just("i am", "hyebeen").subscribe(v-> list.add(v));
        System.out.println("* list : " + list.toString());
    }
}