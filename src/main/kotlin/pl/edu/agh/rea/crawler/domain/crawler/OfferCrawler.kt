package pl.edu.agh.rea.crawler.domain.crawler

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import pl.edu.agh.rea.crawler.configuration.properties.VendorConfiguration
import pl.edu.agh.rea.crawler.domain.scraper.OfferScraper
import pl.edu.agh.rea.crawler.domain.scraper.UrlToScrap
import pl.edu.agh.rea.crawler.domain.sender.OfferSender

@Component
class OfferCrawler(private val vendorConfiguration: VendorConfiguration,
                   private val offerUrlsToScrap: MutableList<UrlToScrap>,
                   private val offerScraper: OfferScraper,
                   private val offerSenders: List<OfferSender>) : Crawler {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(OfferCrawler::class.java)
    }

    override fun run() {
        LOGGER.info("${offerUrlsToScrap.size} remain in queue to scrap")
        val numberOfUrlsToScrap = getNumberOfUrlsToScrap()
        LOGGER.info("Scraping offers from $numberOfUrlsToScrap urls")
        for (i in 0 until numberOfUrlsToScrap) {
            processUrl(offerUrlsToScrap.removeAt(i))
        }
    }

    private fun getNumberOfUrlsToScrap(): Int {
        return if (vendorConfiguration.concurrentRequestsCount < offerUrlsToScrap.size) {
            vendorConfiguration.concurrentRequestsCount
        } else {
            offerUrlsToScrap.size
        }
    }

    private fun processUrl(urlToScrap: UrlToScrap) = GlobalScope.launch {
        val offer = offerScraper.scrap(urlToScrap)
        offerSenders.forEach { it.invoke(offer) }
    }

}