import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class StandardTransitionStrategyTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Test
    @DisplayName("simple standard transition price test")
    void standardTransitionPriceTest() {
        StandardTransitionStrategy strategy = new StandardTransitionStrategy();
        assertEquals(25, strategy.getPrice(10));
    }

    @Test
    @DisplayName("simple print status test")
    void getStatusTest() {
        System.setOut(new PrintStream(outContent));
        TransitionStrategy strategy = new StandardTransitionStrategy();
        strategy.getTransitionStrategy();
        assertEquals("Transition strategy is: Standard", outContent.toString());
        System.setOut(originalOut);
    }

}