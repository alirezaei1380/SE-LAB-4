
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ShipmentTests {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Test
    @DisplayName("set a strategy to a shipment test")
    void setTransitionStrategy() {
        Shipment shipment = new Shipment(20);
        shipment.setTransitionStrategy(new StandardTransitionStrategy());
        assertEquals(50, shipment.executeTransition());
    }

    @Test
    @DisplayName("set a state to a shipment test")
    void setState() {
        System.setOut(new PrintStream(outContent));
        Shipment shipment = new Shipment(24);
        shipment.setState(new DeliveredState());
        assertEquals(shipment.getState());
        assertEquals("package is delivered", outContent.toString());
        System.setOut(originalOut);
    }

}