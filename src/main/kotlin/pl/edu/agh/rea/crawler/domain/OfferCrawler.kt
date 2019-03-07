package pl.edu.agh.rea.crawler.domain

import org.apache.commons.text.StringEscapeUtils
import org.htmlcleaner.HtmlCleaner
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.w3c.dom.Document
import pl.edu.agh.rea.crawler.configuration.provider.ConfigurationProvider
import pl.edu.agh.rea.crawler.domain.extensions.cleanToDocument
import pl.edu.agh.rea.crawler.domain.extensions.getSingleStringValue
import pl.edu.agh.rea.crawler.domain.model.Offer

@Component
class OfferCrawler(private val configurationProvider: ConfigurationProvider,
                   private val htmlCleaner: HtmlCleaner) : Crawler<Offer> {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(OfferCrawler::class.java)
    }

    override suspend fun fetch(url: String): Offer {
        LOGGER.info("Crawling offer from url $url")
        val offerPage = htmlCleaner.cleanToDocument(url)
        return Offer(
                url,
                getStringValue(offerPage, configurationProvider.vendorConfigurationProperties.addressXpath),
                getStringValue(offerPage, configurationProvider.vendorConfigurationProperties.imageXpath),
                getDoubleValue(offerPage, configurationProvider.vendorConfigurationProperties.priceXpath),
                getDoubleValue(offerPage, configurationProvider.vendorConfigurationProperties.areaXpath),
                getIntValue(offerPage, configurationProvider.vendorConfigurationProperties.numberOfRoomsXpath)
        )
    }

    private fun getIntValue(document: Document, xPath: String): Int {
        return getStringValueWithoutNonNumericCharacters(document, xPath).toInt()
    }

    private fun getDoubleValue(document: Document, xPath: String): Double {
        return getStringValueWithoutNonNumericCharacters(document, xPath).toDouble()
    }

    private fun getStringValueWithoutNonNumericCharacters(document: Document, xPath: String): String {
        return getStringValue(document, xPath).replace(Regex("[^0-9.,]"), "").replace(',', '.')
    }

    private fun getStringValue(document: Document, xPath: String): String {
        return StringEscapeUtils.unescapeHtml4(document.getSingleStringValue(xPath)).trim()
    }

}