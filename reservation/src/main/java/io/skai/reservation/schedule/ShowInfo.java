package io.skai.reservation.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ShowInfo {

    @Scheduled(fixedDelay = 10000)
    public void showStatistic() {
        long maxMemory = Runtime.getRuntime().maxMemory();
        log.info("Available processors (cores): "
                + Runtime.getRuntime().availableProcessors());
        log.info("Free memory (bytes): " +
                Runtime.getRuntime().freeMemory());
        log.info("Maximum memory (bytes): " +
                (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory));
    }
}