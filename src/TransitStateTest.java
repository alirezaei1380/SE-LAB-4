import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class TransitStateTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;


    @Test
    @DisplayName("simple transit state status test")
    void testTransitState() {
        System.setOut(new PrintStream(outContent));
        State state = new TransitState();
        state.printStatus();
        assertEquals("package is in transit state", outContent.toString());
        System.setOut(originalOut);
    }

}