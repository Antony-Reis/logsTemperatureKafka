package com.antony.logsTemperatureKafka.consumer.temperature;

import com.antony.logsTemperatureKafka.producer.temperature.RTemperatureDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class TemperatureService {
    private final ITemperatureMongoDBRepository iTemperatureMongoDBRepository;
    private final ITemperaturePostgreRepository iTemperaturePostgreRepository;

    public TemperatureService(ITemperatureMongoDBRepository iTemperatureMongoDBRepository, ITemperaturePostgreRepository iTemperaturePostgreRepository) {
        this.iTemperatureMongoDBRepository = iTemperatureMongoDBRepository;
        this.iTemperaturePostgreRepository = iTemperaturePostgreRepository;
    }

    @KafkaListener(topics = "temperature")
    public void consumeTemperature(RTemperatureDto msg){
        System.out.println("Recebendo: " + msg);

        //Regra de negócio temperature >= 85 é considerado critico
        if (msg.temperature().compareTo(BigDecimal.valueOf(85)) >= 0){
            TemperatureMongoDBEntity temperatureMongoDB = new TemperatureMongoDBEntity(msg.temperature(), msg.instant(), true);
            iTemperatureMongoDBRepository.save(temperatureMongoDB);

            TemperaturePostgreEntity temperaturePostgre = new TemperaturePostgreEntity(msg.temperature(), msg.instant());
            iTemperaturePostgreRepository.save(temperaturePostgre);
            return;
        }

        TemperatureMongoDBEntity temperatureMongoDB = new TemperatureMongoDBEntity(msg.temperature(), msg.instant(), false);
        iTemperatureMongoDBRepository.save(temperatureMongoDB);
    }

    @Scheduled(cron = "0 0 0 * * *", zone = "America/Sao_Paulo")
    public void deleteTemperatures(){
    iTemperatureMongoDBRepository.deleteByInstantLessThanEqual(LocalDateTime.now().minusDays(7));
    }
    
}
