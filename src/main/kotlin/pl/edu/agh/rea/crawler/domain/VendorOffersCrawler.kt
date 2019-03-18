package pl.edu.agh.rea.crawler.domain

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import pl.edu.agh.rea.crawler.configuration.provider.ConfigurationProvider
import pl.edu.agh.rea.crawler.domain.model.Offer
import pl.edu.agh.rea.crawler.domain.store.VisitedSitesStore
import java.util.*

@Component
class VendorOffersCrawler(private val configurationProvider: ConfigurationProvider,
                          private val offerCrawler: OfferCrawler,
                          private val offerUrlsCrawler: OfferUrlsCrawler,
                          private val visitedSitesStore: VisitedSitesStore) : Crawler<List<Offer>> {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(VendorOffersCrawler::class.java)
    }

    override suspend fun fetch(url: String): List<Offer> = coroutineScope {
        LOGGER.info("Crawling configuration (" +
                "ConcurrentRequestCount = ${configurationProvider.vendorConfigurationProperties.concurrentRequestsCount}, " +
                "RequestDelay = ${configurationProvider.vendorConfigurationProperties.requestDelay}, " +
                "PagesToVisit = ${configurationProvider.vendorConfigurationProperties.pagesToVisit}, " +
                "PageParam = ${configurationProvider.vendorConfigurationProperties.pageParam}" +
                ")")
        LOGGER.info("Crawling offers from url $url")
        return@coroutineScope configurationProvider.vendorConfigurationProperties.pages
                .map { url + it.url }
                .map { async { offerUrlsCrawler.fetch(it) } }
                .flatMap { it.await() }
                .chunked(configurationProvider.vendorConfigurationProperties.concurrentRequestsCount.toInt())
                .flatMap { executeBatchOfferCrawling(it) }
    }


    private suspend fun executeBatchOfferCrawling(offerUrls: List<String>): List<Offer> = coroutineScope {
        LOGGER.info("Waiting ${configurationProvider.vendorConfigurationProperties.requestDelay.toLong() / 1000} s")
        delay(configurationProvider.vendorConfigurationProperties.requestDelay.toLong())
        LOGGER.info("Crawling ${offerUrls.size} offers from urls $offerUrls")
        return@coroutineScope offerUrls
                .filter { visitedSitesStore.contains(it).not() }
                .map {
                    async {
                        visitedSitesStore.put(it, Date())
                        offerCrawler.fetch(it)
                    }
                }
                .map { it.await() }
    }

}
