package pl.edu.agh.rea.crawler.configuration

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.htmlcleaner.HtmlCleaner
import org.mapdb.DB
import org.mapdb.DBMaker
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import pl.edu.agh.rea.crawler.configuration.kafka.serializer.OfferSerializer
import pl.edu.agh.rea.crawler.configuration.properties.VendorConfiguration
import pl.edu.agh.rea.crawler.configuration.provider.ConfigurationReader
import pl.edu.agh.rea.crawler.domain.model.Offer

@Configuration
class Beans {

    @Bean
    fun htmlCleaner(): HtmlCleaner = HtmlCleaner()

    @Bean
    fun db(): DB = DBMaker.fileDB("database.db").make()

    @Bean
    fun urlsToScrap(): MutableList<String> = mutableListOf()

    @Bean
    fun vendorConfiguration(configurationReader: ConfigurationReader): VendorConfiguration = configurationReader.readVendorConfiguration()

    @Bean
    fun producerFactory(): ProducerFactory<String, Offer> = DefaultKafkaProducerFactory<String, Offer>(mapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to "localhost:9092",
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to OfferSerializer::class.java
    ))

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, Offer> = KafkaTemplate(producerFactory())

}