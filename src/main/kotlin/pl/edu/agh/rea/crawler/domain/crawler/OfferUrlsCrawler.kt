package pl.edu.agh.rea.crawler.domain.crawler

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import pl.edu.agh.rea.crawler.configuration.properties.PageConfiguration
import pl.edu.agh.rea.crawler.configuration.properties.VendorConfiguration
import pl.edu.agh.rea.crawler.domain.scraper.OfferUrlsScraper
import pl.edu.agh.rea.crawler.domain.scraper.UrlToScrap
import pl.edu.agh.rea.crawler.domain.store.VisitedSitesStore
import java.lang.Thread.sleep

@Component
class OfferUrlsCrawler(private val vendorConfiguration: VendorConfiguration,
                       private val offerUrlsScraper: OfferUrlsScraper,
                       private val offerUrlsToScrap: MutableList<UrlToScrap>,
                       private val visitedSitesStore: VisitedSitesStore) : Crawler {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(OfferUrlsCrawler::class.java)
    }

    override fun run() {
        vendorConfiguration.pages.forEach {
            LOGGER.info("Starting crawling pages from ${it.url}")
            for (i in 1..vendorConfiguration.pagesToVisit) {
                scrapPage(it, i)
            }
            LOGGER.info("Waiting ${vendorConfiguration.requestDelay / 1000} [s]")
            sleep(vendorConfiguration.requestDelay)
        }
    }

    private fun scrapPage(pageConfiguration: PageConfiguration, counter: Int) = GlobalScope.launch {
        offerUrlsScraper
                .scrap(prepareUrlToScrapOfferUrlsFrom(pageConfiguration, counter))
                .filter { !visitedSitesStore.contains(it.url) }
                .forEach { offerUrlsToScrap.add(it) }
    }

    private fun prepareUrlToScrapOfferUrlsFrom(pageConfiguration: PageConfiguration, counter: Int): UrlToScrap {
        return UrlToScrap(
                prepareUrlString(pageConfiguration, counter),
                pageConfiguration.offerType,
                pageConfiguration.propertyType
        )
    }

    private fun prepareUrlString(pageConfiguration: PageConfiguration, counter: Int): String {
        return vendorConfiguration.baseUrl +
                pageConfiguration.url +
                vendorConfiguration.pageParam +
                counter
    }

}