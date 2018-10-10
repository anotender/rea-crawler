package pl.edu.agh.rea.crawler.domain

import org.htmlcleaner.HtmlCleaner
import pl.edu.agh.rea.crawler.configuration.properties.VendorConfigurationProperties
import java.net.URL

class OfferUrlsCrawler(private val vendorConfigurationProperties: VendorConfigurationProperties,
                       private val url: URL,
                       private val htmlCleaner: HtmlCleaner = HtmlCleaner()) : Crawler<List<String>> {

    override suspend fun fetch(): List<String> {
        return htmlCleaner
                .clean(url)
                .evaluateXPath(vendorConfigurationProperties.offerUrlXpath)
                .map { it as String }
    }

}