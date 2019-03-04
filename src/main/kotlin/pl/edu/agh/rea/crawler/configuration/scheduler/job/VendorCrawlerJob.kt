package pl.edu.agh.rea.crawler.configuration.scheduler.job

import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import pl.edu.agh.rea.crawler.configuration.provider.ConfigurationProvider
import pl.edu.agh.rea.crawler.domain.VendorOffersCrawler

@Component
class VendorCrawlerJob(private val configurationProvider: ConfigurationProvider,
                       private val vendorOffersCrawler: VendorOffersCrawler) : Runnable {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(VendorCrawlerJob::class.java)
    }

    override fun run() {
        LOGGER.info("Started crawling")
        val offers = runBlocking { vendorOffersCrawler.fetch(configurationProvider.vendorConfigurationProperties.baseUrl) }
        LOGGER.info("Fetched ${offers.size} offers")
    }

}