package pl.edu.agh.rea.crawler.domain

import org.htmlcleaner.HtmlCleaner
import pl.edu.agh.rea.crawler.configuration.provider.ConfigurationProvider

abstract class BaseCrawlerTest(private val vendorName: String) {

    protected fun getOfferCrawler(): OfferCrawler = OfferCrawler(ConfigurationProvider(vendorName), HtmlCleaner())

    protected fun getOfferUrlsCrawler(): OfferUrlsCrawler = OfferUrlsCrawler(ConfigurationProvider(vendorName), HtmlCleaner())

    protected fun getResourceUrl(resourceName: String): String = this.javaClass.getResource("/$vendorName/$resourceName").toString()

}