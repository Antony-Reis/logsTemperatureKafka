package com.antony.logsTemperatureKafka.consumer.temperature;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ITemperaturePostgreRepository extends JpaRepository<TemperaturePostgreEntity, Long> {
}
