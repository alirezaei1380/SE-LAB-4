import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class DeliveredStateTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;


    @Test
    @DisplayName("simple delivered state status test")
    void testDeliveredState() {
        System.setOut(new PrintStream(outContent));
        State state = new DeliveredState();
        state.printStatus();
        assertEquals("package is delivered", outContent.toString());
        System.setOut(originalOut);
    }

}