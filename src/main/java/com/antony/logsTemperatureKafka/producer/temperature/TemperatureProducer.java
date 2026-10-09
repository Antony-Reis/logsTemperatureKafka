package com.antony.logsTemperatureKafka.producer.temperature;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Service
public class TemperatureProducer {
    private final KafkaTemplate<String, RTemperatureDto> kafkaTemplate;
    private final Sensor sensor;

    public TemperatureProducer(KafkaTemplate<String, RTemperatureDto> kafkaTemplate, Sensor sensor) {
        this.kafkaTemplate = kafkaTemplate;
        this.sensor = sensor;
    }

    public void sendTemperature(RTemperatureDto event){
    kafkaTemplate.send("temperature", event.instant().toString(), event);         //67
    }

    @Scheduled(fixedDelay = 2, timeUnit = TimeUnit.SECONDS)
    public void receivAndSendEvent(){
        RTemperatureDto event = sensor.simulateSensor();

        System.out.println(event);

        sendTemperature(event);
    }




}
