package io.skai.reservation.kafka.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "spring.kafka")
@Configuration
@Getter
@Setter
public class KafkaProperties {
    private String topic;
    private String bootStrapServers;
    private String groupId;
}