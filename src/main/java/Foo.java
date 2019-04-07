import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Foo {
    private static Logger logger = LogManager.getLogger(Foo.class.getSimpleName());

    public String bar() {
        return "foo";
    }

    public void baz(String name) {
        logger.warn("{}, {}", "Hello", name);
    }
}
