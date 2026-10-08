package com.antony.logsTemperatureKafka.producer.temperature;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RTemperatureDto(BigDecimal temperature, LocalDateTime instant) {
}
