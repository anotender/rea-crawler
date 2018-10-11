package pl.edu.agh.rea.crawler.domain

import org.htmlcleaner.HtmlCleaner
import pl.edu.agh.rea.crawler.configuration.properties.VendorConfigurationProperties

abstract class OfferUrlsCrawlerBaseTest {

    protected abstract fun getOfferUrlXpath(): String

    protected fun prepareOfferUrlCrawler(testFilePath: String): OfferUrlsCrawler {
        val url = this.javaClass.getResource(testFilePath)
        val vendorConfigurationProperties = VendorConfigurationProperties()
        vendorConfigurationProperties.offerUrlXpath = getOfferUrlXpath()
        return OfferUrlsCrawler(vendorConfigurationProperties, url, HtmlCleaner())
    }

}