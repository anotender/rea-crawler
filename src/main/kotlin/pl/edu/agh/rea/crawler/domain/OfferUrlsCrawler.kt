package pl.edu.agh.rea.crawler.domain

import org.htmlcleaner.HtmlCleaner
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import pl.edu.agh.rea.crawler.configuration.properties.VendorConfiguration
import pl.edu.agh.rea.crawler.domain.extensions.cleanToDocument
import pl.edu.agh.rea.crawler.domain.extensions.getMultipleStringValue

@Component
class OfferUrlsCrawler(private val vendorConfiguration: VendorConfiguration,
                       private val htmlCleaner: HtmlCleaner) : Crawler<List<String>> {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(OfferUrlsCrawler::class.java)
    }

    override suspend fun fetch(url: String): List<String> {
        LOGGER.info("Scraping offer urls from url $url")
        val offerUrls: List<String> = htmlCleaner
                .cleanToDocument(url)
                .getMultipleStringValue(vendorConfiguration.offerUrlXpath)
        LOGGER.info("Scraped ${offerUrls.size} offer urls")
        return offerUrls
    }

}