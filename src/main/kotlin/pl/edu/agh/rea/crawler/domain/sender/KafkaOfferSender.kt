package pl.edu.agh.rea.crawler.domain.sender

import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import pl.edu.agh.rea.crawler.domain.model.Offer

@Component
class KafkaOfferSender(private val kafkaTemplate: KafkaTemplate<String, Offer>) : OfferSender {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(KafkaOfferSender::class.java)
        private const val TOPIC = "test"
    }

    override fun invoke(offer: Offer) {
        LOGGER.info("Sending offer ${offer.title} to Kafka topic: $TOPIC")
        kafkaTemplate.send(TOPIC, offer)
    }

}