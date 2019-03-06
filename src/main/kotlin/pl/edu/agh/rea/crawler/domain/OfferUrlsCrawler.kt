package pl.edu.agh.rea.crawler.domain

import org.htmlcleaner.HtmlCleaner
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import pl.edu.agh.rea.crawler.configuration.provider.ConfigurationProvider
import pl.edu.agh.rea.crawler.domain.extensions.cleanToDocument
import pl.edu.agh.rea.crawler.domain.extensions.getMultipleStringValue

@Component
class OfferUrlsCrawler(private val configurationProvider: ConfigurationProvider,
                       private val htmlCleaner: HtmlCleaner) : Crawler<List<String>> {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(OfferUrlsCrawler::class.java)
    }

    override suspend fun fetch(url: String): List<String> {
        LOGGER.info("Crawling offer urls from url $url")
        return htmlCleaner
                .cleanToDocument(url)
                .getMultipleStringValue(configurationProvider.vendorConfigurationProperties.offerUrlXpath)
    }

}