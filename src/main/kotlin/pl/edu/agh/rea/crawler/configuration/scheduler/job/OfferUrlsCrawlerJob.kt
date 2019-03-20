package pl.edu.agh.rea.crawler.configuration.scheduler.job

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import pl.edu.agh.rea.crawler.configuration.properties.PageConfiguration
import pl.edu.agh.rea.crawler.configuration.properties.VendorConfiguration
import pl.edu.agh.rea.crawler.domain.OfferUrlsCrawler
import pl.edu.agh.rea.crawler.domain.store.VisitedSitesStore
import java.lang.Thread.sleep

@Component
class OfferUrlsCrawlerJob(private val vendorConfiguration: VendorConfiguration,
                          private val offerUrlsCrawler: OfferUrlsCrawler,
                          private val urlsToScrap: MutableList<String>,
                          private val visitedSitesStore: VisitedSitesStore) : CrawlerJob {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(OfferUrlsCrawlerJob::class.java)
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
        offerUrlsCrawler
                .fetch(prepareUrlToScrapOfferUrlsFrom(pageConfiguration, counter))
                .filter { !visitedSitesStore.contains(it) }
                .forEach { urlsToScrap.add(it) }
    }

    private fun prepareUrlToScrapOfferUrlsFrom(pageConfiguration: PageConfiguration, counter: Int): String {
        return vendorConfiguration.baseUrl +
                pageConfiguration.url +
                vendorConfiguration.pageParam +
                counter
    }

}