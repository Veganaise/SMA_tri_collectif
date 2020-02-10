import org.junit.jupiter.api.Test;
import sma.Systeme;

class SystemeTest {

    @Test
    void startSystem() {
        Systeme systeme=new Systeme(5,2,2);
        systeme.print();
    }
}