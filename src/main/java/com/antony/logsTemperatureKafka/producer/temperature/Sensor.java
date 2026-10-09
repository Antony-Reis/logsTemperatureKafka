package com.antony.logsTemperatureKafka.producer.temperature;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

@Component
public class Sensor {

    private BigDecimal generateRandomNumber(Integer timerSeconds){
        if (timerSeconds == null){
            timerSeconds = 2;
        }
        Random random = new Random();
        double n =  random.nextDouble();
        return BigDecimal.valueOf(n * 100);
    }


    public RTemperatureDto simulateSensor(){
        BigDecimal number = generateRandomNumber(2);
        RTemperatureDto event = new RTemperatureDto(number, LocalDateTime.now());

        return event;
    }}
