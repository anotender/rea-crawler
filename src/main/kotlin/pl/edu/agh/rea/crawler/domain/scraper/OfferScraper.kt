package pl.edu.agh.rea.crawler.domain.scraper

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
class OfferScraper(private val vendorConfiguration: VendorConfiguration,
                   private val htmlCleaner: HtmlCleaner) : Scraper<Offer> {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(OfferScraper::class.java)
    }

    override suspend fun scrap(urlToScrap: UrlToScrap): Offer {
        LOGGER.info("Scraping offer from url $urlToScrap")
        val offerPage = htmlCleaner.cleanToDocument(urlToScrap.url)
        val offer = buildOffer(urlToScrap, offerPage)
        LOGGER.info("Scraped offer from url $urlToScrap successfully")
        return offer
    }

    private fun buildOffer(urlToScrap: UrlToScrap, offerPage: Document): Offer = Offer(
            urlToScrap.url,
            getStringValue(offerPage, vendorConfiguration.addressXpath),
            getStringValue(offerPage, vendorConfiguration.imageXpath),
            getPriceValue(offerPage, vendorConfiguration.priceXpath),
            getDoubleValue(offerPage, vendorConfiguration.areaXpath),
            getIntValue(offerPage, vendorConfiguration.numberOfRoomsXpath),
            getStringValue(offerPage, vendorConfiguration.titleXpath),
            urlToScrap.offerType,
            urlToScrap.propertyType,
            vendorConfiguration.vendor
    )

    //FIXME all of the method below need refactor
    private fun getPriceValue(document: Document, xPath: String): Double? {
        val priceStringValue = getStringValue(document, xPath)
        val euroIndex = priceStringValue?.indexOf("EUR")
        if (euroIndex != null && euroIndex > 0) {
            return priceStringValue
                    .indexOf("EUR")
                    .let {
                        priceStringValue
                                .substring(it + "EUR".length)
                                .let { removeNonNumericCharactersFromString(it) }
                                .toDouble()
                    }
        }
        return priceStringValue
                ?.let { removeNonNumericCharactersFromString(it) }
                ?.toDouble()
    }

    private fun getIntValue(document: Document, xPath: String): Int? {
        return getStringValue(document, xPath)
                ?.let { removeNonNumericCharactersFromString(it) }
                ?.toInt()
    }

    private fun getDoubleValue(document: Document, xPath: String): Double? {
        return getStringValue(document, xPath)
                ?.let { removeNonNumericCharactersFromString(it) }
                ?.toDouble()
    }

    private fun getStringValue(document: Document, xPath: String): String? {
        val stringValue: String = StringEscapeUtils.unescapeHtml4(document.getSingleStringValue(xPath))
                .trim()
                .replace(Regex("\\s+"), " ")
        return if (stringValue.isEmpty()) null else stringValue
    }

    private fun removeNonNumericCharactersFromString(s: String): String {
        return s.replace(Regex("[^0-9.,]"), "").replace(',', '.')
    }

}