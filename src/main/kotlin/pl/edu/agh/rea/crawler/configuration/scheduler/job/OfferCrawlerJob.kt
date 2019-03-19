package pl.edu.agh.rea.crawler.configuration.scheduler.job

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import pl.edu.agh.rea.crawler.configuration.provider.ConfigurationProvider
import pl.edu.agh.rea.crawler.domain.OfferCrawler
import java.lang.Thread.sleep

@Component
class OfferCrawlerJob(private val configurationProvider: ConfigurationProvider,
                      private val urlsToScrap: MutableList<String>,
                      private val offerCrawler: OfferCrawler) : CrawlerJob {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(OfferCrawlerJob::class.java)
    }

    override fun run() {
        while (true) {
            val numberOfUrlsToScrap = getNumberOfUrlsToScrap()
            LOGGER.info("Scraping offers from $numberOfUrlsToScrap urls")
            for (i in 0 until numberOfUrlsToScrap) {
                GlobalScope.launch {
                    println(offerCrawler.fetch(urlsToScrap.removeAt(i)))
                }
            }
            LOGGER.info("Waiting ${configurationProvider.vendorConfigurationProperties.requestDelay / 1000} [s]")
            sleep(configurationProvider.vendorConfigurationProperties.requestDelay)
        }
    }

    private fun getNumberOfUrlsToScrap(): Int {
        return if (configurationProvider.vendorConfigurationProperties.concurrentRequestsCount < urlsToScrap.size) {
            configurationProvider.vendorConfigurationProperties.concurrentRequestsCount
        } else {
            urlsToScrap.size
        }
    }

}