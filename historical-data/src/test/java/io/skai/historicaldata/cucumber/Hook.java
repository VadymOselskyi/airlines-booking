package io.skai.historicaldata.cucumber;

import io.cucumber.java.After;

public class Hook extends SpringIntegrationTest {

    @After
    public void cleanDatabase() {
        jdbcTemplate.execute("TRUNCATE TABLE `airlines-db`.history_ticket");
    }
}