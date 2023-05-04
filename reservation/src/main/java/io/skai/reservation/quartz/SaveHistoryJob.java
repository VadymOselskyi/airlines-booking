package io.skai.reservation.quartz;

import io.skai.reservation.kafka.KafkaProducer;
import io.skai.reservation.service.HistoryTicketService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveHistoryJob implements Job {

    @Autowired
    private HistoryTicketService historyTicketService;
    @Autowired
    private KafkaProducer kafkaProducer;

    @Override
    public void execute(JobExecutionContext context) {
        historyTicketService.prepareHistory().forEach(kafkaProducer::sendMessage);
    }
}