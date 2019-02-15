package pl.edu.agh.rea.crawler.domain

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import pl.edu.agh.rea.crawler.configuration.properties.VendorConfigurationProperties
import pl.edu.agh.rea.crawler.domain.model.Offer

class VendorOffersCrawler(private val vendorConfigurationProperties: VendorConfigurationProperties) : Crawler<List<Offer>> {

    override suspend fun fetch(url: String): List<Offer> = coroutineScope {
        return@coroutineScope vendorConfigurationProperties.pages
                .map { url + it }
                .map { async { getOfferUrls(it) } }
                .flatMap { it.await() }
                .chunked(vendorConfigurationProperties.concurrentRequestsCount.toInt())
                .flatMap { executeBatchOfferCrawling(it) }
    }

    private suspend fun getOfferUrls(url: String): List<String> {
        return OfferUrlsCrawler(vendorConfigurationProperties).fetch(url)
    }

    private suspend fun executeBatchOfferCrawling(offerUrls: List<String>): List<Offer> = coroutineScope {
        delay(vendorConfigurationProperties.requestDelay.toLong())
        return@coroutineScope offerUrls
                .map { async { getOffer(it) } }
                .map { it.await() }
    }

    private suspend fun getOffer(offerUrl: String): Offer {
        return OfferCrawler(vendorConfigurationProperties).fetch(offerUrl)
    }

}
