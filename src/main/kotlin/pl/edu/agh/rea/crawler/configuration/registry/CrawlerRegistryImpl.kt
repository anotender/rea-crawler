package pl.edu.agh.rea.crawler.configuration.registry

import kotlinx.coroutines.experimental.launch
import org.springframework.stereotype.Component
import pl.edu.agh.rea.crawler.configuration.properties.CrawlerConfigurationProperties
import pl.edu.agh.rea.crawler.domain.Crawler
import pl.edu.agh.rea.crawler.domain.VendorOffersCrawler
import pl.edu.agh.rea.crawler.domain.model.Offer
import javax.annotation.PostConstruct

@Component
class CrawlerRegistryImpl(crawlerConfigurationProperties: CrawlerConfigurationProperties) : CrawlerRegistry {

    val crawlers: List<Crawler<List<Offer>>> = crawlerConfigurationProperties
            .vendors
            .map { VendorOffersCrawler(it) }

    @PostConstruct
    private fun foo() {
        crawlers.forEach { launch { it.fetch() } }
    }

}