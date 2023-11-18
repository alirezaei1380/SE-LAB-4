import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StandardTransitionStrategyTest {

    @Test
    @DisplayName("simple standard transition price test")
    void standardTransitionPriceTest() {
        StandardTransitionStrategy strategy = new StandardTransitionStrategy();
        assertEquals(25, strategy.getPrice(10));
    }

}