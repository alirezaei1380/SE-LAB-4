import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressTransitionStrategyTest {

    @Test
    @DisplayName("simple express transition price test")
    void expressTransitionPriceTest() {
        ExpressTransitionStrategy strategy = new ExpressTransitionStrategy();
        assertEquals(35, strategy.getPrice(10));
    }
}