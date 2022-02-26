import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class PubSubTest {

    @Test
    public void test1() {
        Publisher publisher1 = new Publisher(1);
        Publisher publisher2 = new Publisher(2);

        Subscriber subscriber1 = new Subscriber(1);
        Subscriber subscriber2 = new Subscriber(1,2);

        publisher1.publish("hello subscribers~");
        publisher2.publish("this is publisher!");
    }

}

class ContentServer {
    private HashMap<Integer , List<Subscriber>> subscriberLists;

    private static ContentServer serverInstance;

    public static ContentServer getInstance() {
        if (serverInstance == null) {
            serverInstance = new ContentServer();
        }
        return serverInstance;
    }

    private ContentServer() {
        this.subscriberLists = new HashMap<>();
    }

    public void sendMessage(int topic, String message) {
        List<Subscriber> subs = subscriberLists.get(topic);
        if (subs == null) {
            System.out.println("no subscribers");
            return;
        }

        for (Subscriber s : subs) {
            s.receivedMessage(topic, message);
        }
    }

    public void registerSubscriber(Subscriber s, int topic) {
        List<Subscriber> subscribers = subscriberLists.getOrDefault(topic, new LinkedList<>());
        subscribers.add(s);
        subscriberLists.put(topic, subscribers);
    }
}

class Publisher {
    private int topic;
    public Publisher(int topic) {
        this.topic = topic;
    }

    public void publish(String message) {
        ContentServer.getInstance().sendMessage(this.topic, message);
    }
}

class Subscriber {
    public Subscriber(int...topicList) {
        for (int topic : topicList) {
            ContentServer.getInstance().registerSubscriber(this, topic);
        }
    }

    public void receivedMessage(int topic, String message) {
//        switch (topic) {
//            case 1:
//                break;
//            case 2:
//                break;
//        }
        System.out.println("CH." + topic + ">> received Message : " + message);
    }
}

/** Reference
 * https://riptutorial.com/design-patterns/example/6498/publish-subscribe-in-java
 */