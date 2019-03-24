package pl.edu.agh.rea.crawler.configuration.scheduler.job

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import pl.edu.agh.rea.crawler.configuration.kafka.producer.OfferSender
import pl.edu.agh.rea.crawler.configuration.properties.VendorConfiguration
import pl.edu.agh.rea.crawler.domain.OfferCrawler

@Component
class OfferCrawlerJob(private val vendorConfiguration: VendorConfiguration,
                      private val urlsToScrap: MutableList<String>,
                      private val offerCrawler: OfferCrawler,
                      private val offerSender: OfferSender) : CrawlerJob {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(OfferCrawlerJob::class.java)
    }

    override fun run() {
        LOGGER.info("${urlsToScrap.size} remain in queue to scrap")
        val numberOfUrlsToScrap = getNumberOfUrlsToScrap()
        LOGGER.info("Scraping offers from $numberOfUrlsToScrap urls")
        for (i in 0 until numberOfUrlsToScrap) {
            processUrl(urlsToScrap.removeAt(i))
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