package pl.edu.agh.rea.crawler.configuration.scheduler.job

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import pl.edu.agh.rea.crawler.configuration.properties.PageConfigurationProperties
import pl.edu.agh.rea.crawler.configuration.provider.ConfigurationProvider
import pl.edu.agh.rea.crawler.domain.OfferUrlsCrawler
import pl.edu.agh.rea.crawler.domain.store.VisitedSitesStore
import java.lang.Thread.sleep

@Component
class OfferUrlsCrawlerJob(private val configurationProvider: ConfigurationProvider,
                          private val offerUrlsCrawler: OfferUrlsCrawler,
                          private val urlsToScrap: MutableList<String>,
                          private val visitedSitesStore: VisitedSitesStore) : CrawlerJob {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(OfferUrlsCrawlerJob::class.java)
    }

    override fun run() {
        configurationProvider.vendorConfigurationProperties.pages.forEach {
            LOGGER.info("Starting crawling pages from ${it.url}")
            for (i in 1..configurationProvider.vendorConfigurationProperties.pagesToVisit) {
                scrapPage(it, i)
            }
            LOGGER.info("Waiting ${configurationProvider.vendorConfigurationProperties.requestDelay / 1000} [s]")
            sleep(configurationProvider.vendorConfigurationProperties.requestDelay)
        }
    }

    private fun scrapPage(pageConfigurationProperties: PageConfigurationProperties, counter: Int) = GlobalScope.launch {
        offerUrlsCrawler
                .fetch(prepareUrlToScrapOfferUrlsFrom(pageConfigurationProperties, counter))
                .filter { !visitedSitesStore.contains(it) }
                .forEach { urlsToScrap.add(it) }
    }

    private fun prepareUrlToScrapOfferUrlsFrom(pageConfigurationProperties: PageConfigurationProperties, counter: Int): String {
        return configurationProvider.vendorConfigurationProperties.baseUrl +
                pageConfigurationProperties.url +
                configurationProvider.vendorConfigurationProperties.pageParam +
                counter
    }

}