import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ExpressTransitionStrategyTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Test
    @DisplayName("simple express transition price test")
    void expressTransitionPriceTest() {
        ExpressTransitionStrategy strategy = new ExpressTransitionStrategy();
        assertEquals(35, strategy.getPrice(10));
    }

    @Test
    @DisplayName("simple print status test")
    void getStatusTest() {
        System.setOut(new PrintStream(outContent));
        TransitionStrategy strategy = new ExpressTransitionStrategy();
        strategy.getTransitionStrategy();
        assertEquals("Transition strategy is: EXPRESS", outContent.toString());
        System.setOut(originalOut);
    }
}