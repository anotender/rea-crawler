package pl.edu.agh.rea.crawler.configuration.scheduler.job

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import pl.edu.agh.rea.crawler.configuration.kafka.producer.OfferSender
import pl.edu.agh.rea.crawler.configuration.properties.VendorConfiguration
import pl.edu.agh.rea.crawler.domain.OfferCrawler
import java.lang.Thread.sleep

@Component
class OfferCrawlerJob(private val vendorConfiguration: VendorConfiguration,
                      private val urlsToScrap: MutableList<String>,
                      private val offerCrawler: OfferCrawler,
                      private val offerSender: OfferSender) : CrawlerJob {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(OfferCrawlerJob::class.java)
    }

    override fun run() {
        while (true) {
            val numberOfUrlsToScrap = getNumberOfUrlsToScrap()
            LOGGER.info("Scraping offers from $numberOfUrlsToScrap urls")
            for (i in 0 until numberOfUrlsToScrap) {
                processUrl(urlsToScrap.removeAt(i))
            }
            LOGGER.info("Waiting ${vendorConfiguration.requestDelay / 1000} [s]")
            LOGGER.info("${urlsToScrap.size} remain in queue to scrap")
            sleep(vendorConfiguration.requestDelay)
        }
    }

    private fun getNumberOfUrlsToScrap(): Int {
        return if (vendorConfiguration.concurrentRequestsCount < urlsToScrap.size) {
            vendorConfiguration.concurrentRequestsCount
        } else {
            urlsToScrap.size
        }
    }

    private fun processUrl(url: String) = GlobalScope.launch {
        val offer = offerCrawler.fetch(url)
        offerSender.sendOffer(offer)
    }

}