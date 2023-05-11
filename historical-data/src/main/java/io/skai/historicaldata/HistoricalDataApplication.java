package io.skai.historicaldata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class HistoricalDataApplication {
    public static void main(String[] args) {
        SpringApplication.run(HistoricalDataApplication.class, args);
    }
}