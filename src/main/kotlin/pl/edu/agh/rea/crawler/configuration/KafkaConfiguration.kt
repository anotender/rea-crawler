package pl.edu.agh.rea.crawler.configuration

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer
import pl.edu.agh.rea.crawler.domain.model.Offer

@Configuration
class KafkaConfiguration {

    @Bean
    fun producerFactory(): ProducerFactory<String, Offer> = DefaultKafkaProducerFactory<String, Offer>(mapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to "localhost:9092",
            JsonSerializer.ADD_TYPE_INFO_HEADERS to false,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to JsonSerializer::class.java
    ))

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, Offer> = KafkaTemplate(producerFactory())

}