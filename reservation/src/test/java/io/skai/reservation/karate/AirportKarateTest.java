package io.skai.reservation.karate;

import com.intuit.karate.junit5.Karate;

public class AirportKarateTest extends SpringKarateTest {

    @Karate.Test
    public Karate karateTest() {
        return Karate.run("airport").relativeTo(getClass());
    }
}