package com.antony.logsTemperatureKafka.consumer.temperature;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;

public interface ITemperatureMongoDBRepository extends MongoRepository<TemperatureMongoDBEntity, String> {
    long deleteByInstantLessThanEqual(LocalDateTime instant);
}
