import org.junit.jupiter.api.Test;
import pubsub.MyPublisher;
import pubsub.ReactiveApp;

class ReactiveAppTest {

    private final ReactiveApp app;

    public ReactiveAppTest() {
        this.app = new ReactiveApp();
    }

    @Test
    public void testMono1() {
        MyPublisher publisher = new MyPublisher();
        app.mono1(publisher);
        app.mono1(publisher);
    }

    @Test
    public void testMono2() {
        app.mono2();
    }

    @Test
    public void testFlux1() {
        app.flux1(new MyPublisher());
    }

    @Test
    public void testFlux2() {
        app.flux2();
    }

}