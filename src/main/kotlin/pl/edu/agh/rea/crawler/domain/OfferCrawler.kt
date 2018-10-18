package pl.edu.agh.rea.crawler.domain

import org.htmlcleaner.HtmlCleaner
import org.htmlcleaner.TagNode
import pl.edu.agh.rea.crawler.configuration.properties.VendorConfigurationProperties
import pl.edu.agh.rea.crawler.domain.model.Offer
import pl.edu.agh.rea.crawler.domain.xpath.SingleStringValueXPathEvaluator
import pl.edu.agh.rea.crawler.domain.xpath.XPathEvaluator
import java.net.URL

class OfferCrawler(private val vendorConfigurationProperties: VendorConfigurationProperties,
                   private val offerUrl: URL,
                   private val htmlCleaner: HtmlCleaner = HtmlCleaner(),
                   private val singleStringValueXPathEvaluator: XPathEvaluator<String> = SingleStringValueXPathEvaluator()) : Crawler<Offer> {

    override suspend fun fetch(): Offer {
        val offerPage = htmlCleaner.clean(offerUrl)
        return Offer(
                offerUrl.toString(),
                getStringValue(offerPage, vendorConfigurationProperties.addressXpath),
                getStringValue(offerPage, vendorConfigurationProperties.imageXpath),
                getStringValue(offerPage, vendorConfigurationProperties.priceXpath),
                getIntValue(offerPage, vendorConfigurationProperties.numberOfRoomsXpath)
        )
    }

    private fun getStringValue(tagNode: TagNode, xPath: String): String {
        return singleStringValueXPathEvaluator.evaluate(tagNode, xPath).trim()
    }

    private fun getIntValue(tagNode: TagNode, xPath: String): Int {
        return getStringValue(tagNode, xPath).toInt()
    }

}