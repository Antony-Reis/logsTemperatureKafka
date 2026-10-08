package com.antony.logsTemperatureKafka.producer.temperature;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class temperatureProducer {
    private final KafkaTemplate<String, RTemperatureDto> kafkaTemplate;

    public temperatureProducer(KafkaTemplate<String, RTemperatureDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTemperature(RTemperatureDto event){
    kafkaTemplate.send("temperature", event.instant().toString(), event);         //67
    }

    private BigDecimal generateRandomNumber(Integer timerSeconds){
    if (timerSeconds == null){
        timerSeconds = 2;
    }
    Random random = new Random();
    double n =  random.nextDouble();
        return BigDecimal.valueOf(n * 10);
}

 public void simulateIO(){
    while (true){
        BigDecimal number = generateRandomNumber(2);
        RTemperatureDto event = new RTemperatureDto(number, LocalDateTime.now());

        System.out.println(event);

        sendTemperature(event);
        try{
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("A thread foi interrompida.");
        }
    }
 }
}
