package pl.edu.agh.rea.crawler.domain

import org.apache.commons.text.StringEscapeUtils
import org.htmlcleaner.HtmlCleaner
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.w3c.dom.Document
import pl.edu.agh.rea.crawler.configuration.properties.VendorConfiguration
import pl.edu.agh.rea.crawler.domain.extensions.cleanToDocument
import pl.edu.agh.rea.crawler.domain.extensions.getSingleStringValue
import pl.edu.agh.rea.crawler.domain.model.Offer

@Component
class OfferCrawler(private val vendorConfiguration: VendorConfiguration,
                   private val htmlCleaner: HtmlCleaner) : Crawler<Offer> {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(OfferCrawler::class.java)
    }

    override suspend fun fetch(url: String): Offer {
        LOGGER.info("Scraping offer from url $url")
        val offerPage = htmlCleaner.cleanToDocument(url)
        val offer = buildOffer(url, offerPage)
        LOGGER.info("Scraped offer from url $url successfully")
        return offer
    }

    private fun buildOffer(url: String, offerPage: Document): Offer = Offer(
            url,
            getStringValue(offerPage, vendorConfiguration.addressXpath),
            getStringValue(offerPage, vendorConfiguration.imageXpath),
            getDoubleValue(offerPage, vendorConfiguration.priceXpath),
            getDoubleValue(offerPage, vendorConfiguration.areaXpath),
            getIntValue(offerPage, vendorConfiguration.numberOfRoomsXpath),
            getStringValue(offerPage, vendorConfiguration.titleXpath)
    )

    private fun getIntValue(document: Document, xPath: String): Int? {
        return getStringValueWithoutNonNumericCharacters(document, xPath)?.toInt()
    }

    private fun getDoubleValue(document: Document, xPath: String): Double? {
        return getStringValueWithoutNonNumericCharacters(document, xPath)?.toDouble()
    }

    private fun getStringValueWithoutNonNumericCharacters(document: Document, xPath: String): String? {
        return getStringValue(document, xPath)
                ?.replace(Regex("[^0-9.,]"), "")
                ?.replace(',', '.')
    }

    private fun getStringValue(document: Document, xPath: String): String? {
        val stringValue: String = StringEscapeUtils.unescapeHtml4(document.getSingleStringValue(xPath))
                .trim()
                .replace(Regex("\\s+"), " ")
        return if (stringValue.isEmpty()) null else stringValue
    }

}