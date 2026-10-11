package com.antony.logsTemperatureKafka.consumer.temperature;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "temperature")
public class TemperaturePostgreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "temperature", nullable = false)
    private BigDecimal temperature;

    @Column(name = "instant", nullable = false)
    private LocalDateTime instant;

    public TemperaturePostgreEntity() {
    }

    public TemperaturePostgreEntity(BigDecimal temperature, LocalDateTime instant) {
        this.temperature = temperature;
        this.instant = instant;
    }

    public long getId() {
        return id;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public LocalDateTime getInstant() {
        return instant;
    }

    public void setInstant(LocalDateTime instant) {
        this.instant = instant;
    }
}
