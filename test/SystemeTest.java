import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SystemeTest {

    @Test
    void startSystem() {
        Systeme systeme=new Systeme(5,2,2);
        systeme.print();
    }
}