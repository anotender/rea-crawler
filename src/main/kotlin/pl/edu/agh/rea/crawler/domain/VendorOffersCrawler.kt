package pl.edu.agh.rea.crawler.domain

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import org.slf4j.LoggerFactory
import pl.edu.agh.rea.crawler.configuration.properties.VendorConfigurationProperties
import pl.edu.agh.rea.crawler.domain.model.Offer

class VendorOffersCrawler(private val vendorConfigurationProperties: VendorConfigurationProperties) : Crawler<List<Offer>> {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(VendorOffersCrawler::class.java)
    }

    private val offerCrawler: OfferCrawler = OfferCrawler(vendorConfigurationProperties)
    private val offerUrlsCrawler: OfferUrlsCrawler = OfferUrlsCrawler(vendorConfigurationProperties)

    override suspend fun fetch(url: String): List<Offer> = coroutineScope {
        LOGGER.info("[${vendorConfigurationProperties.name}] Crawling configuration (" +
                "ConcurrentRequestCount = ${vendorConfigurationProperties.concurrentRequestsCount}, " +
                "RequestDelay = ${vendorConfigurationProperties.requestDelay}, " +
                "PagesToVisit = ${vendorConfigurationProperties.pagesToVisit}, " +
                "PageParam = ${vendorConfigurationProperties.pageParam}" +
                ")")
        LOGGER.info("[${vendorConfigurationProperties.name}] Crawling offers from url $url")
        return@coroutineScope vendorConfigurationProperties.pages
                .map { url + it }
                .map { async { offerUrlsCrawler.fetch(it) } }
                .flatMap { it.await() }
                .chunked(vendorConfigurationProperties.concurrentRequestsCount.toInt())
                .flatMap { executeBatchOfferCrawling(it) }
    }


    private suspend fun executeBatchOfferCrawling(offerUrls: List<String>): List<Offer> = coroutineScope {
        LOGGER.info("[${vendorConfigurationProperties.name}] Waiting ${vendorConfigurationProperties.requestDelay.toLong() / 1000} s")
        delay(vendorConfigurationProperties.requestDelay.toLong())
        LOGGER.info("[${vendorConfigurationProperties.name}] Crawling ${offerUrls.size} offers from urls $offerUrls")
        return@coroutineScope offerUrls
                .map { async { offerCrawler.fetch(it) } }
                .map { it.await() }
    }

}
