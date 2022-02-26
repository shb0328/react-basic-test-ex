package pubsub;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class MyPublisher implements Publisher {
    @Override
    public void subscribe(Subscriber s) {
        System.out.println("*** pubsub.MyPublisher.subscribe ***");

        Subscription subscription = new Subscription() {
            @Override
            public void request(long n) {
                System.out.println("*** pubsub.MyPublisher.subscribe.subscription.request 동작 시작 ***");
                s.onNext("aaaa");
                s.onNext("bbbb");
                s.onComplete();
                System.out.println("*** pubsub.MyPublisher.subscribe.subscription.request 동작 완료 ***");
            }

            @Override
            public void cancel() {
                System.out.println("pubsub.MyPublisher.cancel");
            }
        };

        System.out.println("시작");
        s.onSubscribe(subscription);
        System.out.println("종료");
    }
}