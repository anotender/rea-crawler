package pl.edu.agh.rea.crawler.domain.scraper

import org.htmlcleaner.HtmlCleaner
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import pl.edu.agh.rea.crawler.configuration.properties.VendorConfiguration
import pl.edu.agh.rea.crawler.domain.extensions.cleanToDocument
import pl.edu.agh.rea.crawler.domain.extensions.getMultipleStringValue

@Component
class OfferUrlsScraper(private val vendorConfiguration: VendorConfiguration,
                       private val htmlCleaner: HtmlCleaner) : Scraper<List<UrlToScrap>> {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(OfferUrlsScraper::class.java)
    }

    override suspend fun scrap(urlToScrap: UrlToScrap): List<UrlToScrap> {
        LOGGER.info("Scraping offer urls from url $urlToScrap")
        val offerUrls: List<UrlToScrap> = htmlCleaner
                .cleanToDocument(urlToScrap.url)
                .getMultipleStringValue(vendorConfiguration.offerUrlXpath)
                .map { addVendorBaseUrlIfNeeded(it) }
                .map { UrlToScrap(it, urlToScrap.offerType, urlToScrap.propertyType) }
        LOGGER.info("Scraped ${offerUrls.size} offer urls")
        return offerUrls
    }

    private fun addVendorBaseUrlIfNeeded(url: String) = when (url.contains(vendorConfiguration.baseUrl)) {
        true -> url
        false -> vendorConfiguration.baseUrl + url
    }

}