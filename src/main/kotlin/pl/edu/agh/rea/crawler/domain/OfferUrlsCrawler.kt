package pl.edu.agh.rea.crawler.domain

import org.htmlcleaner.HtmlCleaner
import pl.edu.agh.rea.crawler.configuration.properties.VendorConfigurationProperties
import pl.edu.agh.rea.crawler.domain.htmlcleaner.extensions.cleanStringUrl
import pl.edu.agh.rea.crawler.domain.htmlcleaner.extensions.getMultipleStringValue

class OfferUrlsCrawler(private val vendorConfigurationProperties: VendorConfigurationProperties,
                       private val htmlCleaner: HtmlCleaner = HtmlCleaner()) : Crawler<List<String>> {

    override suspend fun fetch(url: String): List<String> {
        return htmlCleaner
                .cleanStringUrl(url)
                .getMultipleStringValue(vendorConfigurationProperties.offerUrlXpath)
    }

}