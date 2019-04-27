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
            offerUrl = urlToScrap.url,
            address = getStringValue(offerPage, vendorConfiguration.addressXpath),
            imageUrl = getStringValue(offerPage, vendorConfiguration.imageXpath),
            price = getPriceValue(offerPage, vendorConfiguration.priceXpath),
            area = getDoubleValue(offerPage, vendorConfiguration.areaXpath),
            numberOfRooms = getIntValue(offerPage, vendorConfiguration.numberOfRoomsXpath),
            floor = getFloor(offerPage, vendorConfiguration.floorXpath),
            yearOfConstruction = getIntValue(offerPage, vendorConfiguration.yearOfConstructionXpath),
            title = getStringValue(offerPage, vendorConfiguration.titleXpath),
            offerType = urlToScrap.offerType,
            propertyType = urlToScrap.propertyType,
            vendor = vendorConfiguration.vendor
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

    private fun getFloor(document: Document, xPath: String): Int? {
        return getStringValue(document, xPath)
                ?.substringBefore('/')
                ?.let {
                    if (it.contains("parter", true)) {
                        return@let "0"
                    } else {
                        return@let removeNonNumericCharactersFromString(it)
                    }
                }
                ?.toInt()
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