package com.example.countclicks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ClickCounterControllerUnitTest {

    @Test
    void counterStartsAtZero() {
        ClickCounterController controller = new ClickCounterController();

        assertEquals(0, controller.getClicks().count());
    }

    @Test
    void eachClickIncrementsTheCounter() {
        ClickCounterController controller = new ClickCounterController();

        assertEquals(1, controller.incrementClicks().count());
        assertEquals(2, controller.incrementClicks().count());
        assertEquals(2, controller.getClicks().count());
    }

    @Test
    void healthEndpointReportsApplicationIsUp() {
        ClickCounterController controller = new ClickCounterController();

        assertEquals(new ClickCounterController.HealthStatus("UP", "countclicks"), controller.getHealth());
    }
}
