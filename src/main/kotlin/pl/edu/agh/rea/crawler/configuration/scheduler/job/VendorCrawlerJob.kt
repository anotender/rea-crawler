package pl.edu.agh.rea.crawler.configuration.scheduler.job

import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import pl.edu.agh.rea.crawler.configuration.properties.VendorConfigurationProperties
import pl.edu.agh.rea.crawler.domain.VendorOffersCrawler

class VendorCrawlerJob(private val vendorConfigurationProperties: VendorConfigurationProperties) : Runnable {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(VendorCrawlerJob::class.java)
    }

    private val crawler = VendorOffersCrawler(vendorConfigurationProperties)

    override fun run() {
        LOGGER.info("[${vendorConfigurationProperties.name}] Started crawling")
        val offers = runBlocking { crawler.fetch(vendorConfigurationProperties.baseUrl) }
        LOGGER.info("[${vendorConfigurationProperties.name}] Fetched ${offers.size} offers")
    }

}