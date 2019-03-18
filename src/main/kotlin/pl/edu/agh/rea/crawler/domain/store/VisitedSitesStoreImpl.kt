package pl.edu.agh.rea.crawler.domain.store

import org.mapdb.DB
import org.mapdb.Serializer
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.*
import javax.annotation.PreDestroy

@Component
class VisitedSitesStoreImpl(db: DB) : VisitedSitesStore {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(VisitedSitesStoreImpl::class.java)
    }

    private val visitedSites = db.hashMap("visitedSites")
            .keySerializer(Serializer.STRING)
            .valueSerializer(Serializer.DATE)
            .createOrOpen()

    override fun put(k: String, v: Date) {
        visitedSites.putIfAbsent(k, v)
    }

    override fun contains(k: String): Boolean {
        return visitedSites.containsKey(k)
    }

    @PreDestroy
    private fun preDestroy() {
        LOGGER.info("Closing connection to visited sites")
        visitedSites.close()
    }

}