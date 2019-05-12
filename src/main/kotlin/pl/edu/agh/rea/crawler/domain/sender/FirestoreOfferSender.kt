package pl.edu.agh.rea.crawler.domain.sender

import com.google.cloud.firestore.Firestore
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import pl.edu.agh.rea.crawler.domain.model.Offer

//@Component
class FirestoreOfferSender(private val db: Firestore) : OfferSender {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(FirestoreOfferSender::class.java)
        private const val OFFERS_COLLECTION: String = "offers"
    }

    override fun invoke(offer: Offer) {
        LOGGER.info("Sending offer ${offer.title} to Firestore")
        db.collection(OFFERS_COLLECTION).add(offer)
    }

}