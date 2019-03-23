package pl.edu.agh.rea.crawler.configuration.kafka.serializer

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.apache.kafka.common.serialization.Serializer
import pl.edu.agh.rea.crawler.domain.model.Offer

class OfferSerializer : Serializer<Offer> {

    override fun configure(p0: MutableMap<String, *>?, p1: Boolean) {}

    override fun serialize(p0: String?, p1: Offer?): ByteArray {
        return jacksonObjectMapper().writeValueAsBytes(p1)
    }

    override fun close() {}

}