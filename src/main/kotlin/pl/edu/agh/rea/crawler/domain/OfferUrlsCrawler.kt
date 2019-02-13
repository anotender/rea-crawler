package pl.edu.agh.rea.crawler.domain

import org.htmlcleaner.HtmlCleaner
import pl.edu.agh.rea.crawler.configuration.properties.VendorConfigurationProperties
import pl.edu.agh.rea.crawler.domain.xpath.MultipleStringValueXPathEvaluator
import pl.edu.agh.rea.crawler.domain.xpath.XPathEvaluator
import java.net.URL

class OfferUrlsCrawler(private val vendorConfigurationProperties: VendorConfigurationProperties,
                       private val url: URL,
                       private val htmlCleaner: HtmlCleaner = HtmlCleaner(),
                       private val multipleStringValueXPathEvaluator: XPathEvaluator<List<String>> = MultipleStringValueXPathEvaluator()) : Crawler<List<String>> {

    override suspend fun fetch(): List<String> {
        return multipleStringValueXPathEvaluator.evaluate(
                htmlCleaner.clean(url),
                vendorConfigurationProperties.offerUrlXpath
        )
    }

}