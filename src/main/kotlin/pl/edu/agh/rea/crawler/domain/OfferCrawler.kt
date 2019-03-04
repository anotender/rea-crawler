package pl.edu.agh.rea.crawler.domain

import org.htmlcleaner.HtmlCleaner
import org.htmlcleaner.TagNode
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import pl.edu.agh.rea.crawler.configuration.provider.ConfigurationProvider
import pl.edu.agh.rea.crawler.domain.htmlcleaner.extensions.cleanStringUrl
import pl.edu.agh.rea.crawler.domain.htmlcleaner.extensions.getSingleIntValue
import pl.edu.agh.rea.crawler.domain.htmlcleaner.extensions.getSingleStringValue
import pl.edu.agh.rea.crawler.domain.model.Offer

@Component
class OfferCrawler(private val configurationProvider: ConfigurationProvider,
                   private val htmlCleaner: HtmlCleaner) : Crawler<Offer> {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(OfferCrawler::class.java)
    }

    override suspend fun fetch(url: String): Offer {
        LOGGER.info("Crawling offer from url $url")
        val offerPage = htmlCleaner.cleanStringUrl(url)
        return Offer(
                url,
                getStringValue(offerPage, configurationProvider.vendorConfigurationProperties.addressXpath),
                getStringValue(offerPage, configurationProvider.vendorConfigurationProperties.imageXpath),
                getStringValue(offerPage, configurationProvider.vendorConfigurationProperties.priceXpath),
                getStringValue(offerPage, configurationProvider.vendorConfigurationProperties.areaXpath),
                getIntValue(offerPage, configurationProvider.vendorConfigurationProperties.numberOfRoomsXpath)
        )
    }

    private fun getStringValue(tagNode: TagNode, xPath: String): String {
        return tagNode.getSingleStringValue(xPath).orEmpty().trim()
    }

    private fun getIntValue(tagNode: TagNode, xPath: String): Int {
        return tagNode.getSingleIntValue(xPath) ?: 0
    }

}