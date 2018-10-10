package pl.edu.agh.rea.crawler.domain

import org.htmlcleaner.HtmlCleaner
import org.htmlcleaner.TagNode
import pl.edu.agh.rea.crawler.configuration.properties.VendorConfigurationProperties
import pl.edu.agh.rea.crawler.domain.model.Offer
import pl.edu.agh.rea.crawler.domain.xpath.XPathEvaluatorFactory
import java.net.URL

class OfferCrawler(private val vendorConfigurationProperties: VendorConfigurationProperties,
                   private val offerUrl: URL,
                   private val htmlCleaner: HtmlCleaner = HtmlCleaner(),
                   private val xPathEvaluatorFactory: XPathEvaluatorFactory = XPathEvaluatorFactory()) : Crawler<Offer> {

    override suspend fun fetch(): Offer {
        return Offer(
                "", "", "", ""
        )
    }

    private fun buildOffer(tagNode: TagNode): Offer {
        return Offer(
                "", "", "", ""
        )
    }

    private fun getTextValue(tagNode: TagNode, xPath: String): String {
        return xPathEvaluatorFactory
                .textValueXPathEvaluator
                .evaluate(tagNode, xPath) ?: ""
    }

    private fun getAttributeValue(tagNode: TagNode, xPath: String): String {
        return xPathEvaluatorFactory
                .attributeValueXPathEvaluator
                .evaluate(tagNode, xPath) ?: ""
    }

}