package pl.edu.agh.rea.crawler.domain

import org.htmlcleaner.HtmlCleaner
import org.slf4j.LoggerFactory
import pl.edu.agh.rea.crawler.configuration.properties.VendorConfigurationProperties
import pl.edu.agh.rea.crawler.domain.htmlcleaner.extensions.cleanStringUrl
import pl.edu.agh.rea.crawler.domain.htmlcleaner.extensions.getMultipleStringValue

class OfferUrlsCrawler(private val vendorConfigurationProperties: VendorConfigurationProperties,
                       private val htmlCleaner: HtmlCleaner = HtmlCleaner()) : Crawler<List<String>> {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(OfferUrlsCrawler::class.java)
    }

    override suspend fun fetch(url: String): List<String> {
        LOGGER.info("[${vendorConfigurationProperties.name}] Crawling offer urls from url $url")
        return htmlCleaner
                .cleanStringUrl(url)
                .getMultipleStringValue(vendorConfigurationProperties.offerUrlXpath)
    }

}