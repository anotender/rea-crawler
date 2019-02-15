package pl.edu.agh.rea.crawler.domain

import org.htmlcleaner.HtmlCleaner
import org.htmlcleaner.TagNode
import org.slf4j.LoggerFactory
import pl.edu.agh.rea.crawler.configuration.properties.VendorConfigurationProperties
import pl.edu.agh.rea.crawler.domain.htmlcleaner.extensions.cleanStringUrl
import pl.edu.agh.rea.crawler.domain.htmlcleaner.extensions.getSingleIntValue
import pl.edu.agh.rea.crawler.domain.htmlcleaner.extensions.getSingleStringValue
import pl.edu.agh.rea.crawler.domain.model.Offer

class OfferCrawler(private val vendorConfigurationProperties: VendorConfigurationProperties,
                   private val htmlCleaner: HtmlCleaner = HtmlCleaner()) : Crawler<Offer> {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(OfferCrawler::class.java)
    }

    override suspend fun fetch(url: String): Offer {
        LOGGER.info("[${vendorConfigurationProperties.name}] Crawling offer from url $url")
        val offerPage = htmlCleaner.cleanStringUrl(url)
        return Offer(
                url,
                getStringValue(offerPage, vendorConfigurationProperties.addressXpath),
                getStringValue(offerPage, vendorConfigurationProperties.imageXpath),
                getStringValue(offerPage, vendorConfigurationProperties.priceXpath),
                getStringValue(offerPage, vendorConfigurationProperties.areaXpath),
                getIntValue(offerPage, vendorConfigurationProperties.numberOfRoomsXpath)
        )
    }

    private fun getStringValue(tagNode: TagNode, xPath: String): String {
        return tagNode.getSingleStringValue(xPath).orEmpty().trim()
    }

    private fun getIntValue(tagNode: TagNode, xPath: String): Int {
        return tagNode.getSingleIntValue(xPath) ?: 0
    }

}