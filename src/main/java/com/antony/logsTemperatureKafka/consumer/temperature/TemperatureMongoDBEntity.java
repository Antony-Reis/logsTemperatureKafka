package com.antony.logsTemperatureKafka.consumer.temperature;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "temperature")
public class TemperatureMongoDBEntity {
    @Id
    private String id;

    private BigDecimal temperature;

    @Indexed
    private LocalDateTime instant;

    private boolean isCritcal;

    public TemperatureMongoDBEntity() {
    }

    public TemperatureMongoDBEntity(BigDecimal temperature, LocalDateTime instant, boolean isCritcal) {
        this.temperature = temperature;
        this.instant = instant;
        this.isCritcal = isCritcal;
    }

    public String getId() {
        return id;
    }

    public TemperatureMongoDBEntity(String id, BigDecimal temperature, LocalDateTime instant, boolean isCritcal) {
        this.id = id;
        this.temperature = temperature;
        this.instant = instant;
        this.isCritcal = isCritcal;
    }
}
