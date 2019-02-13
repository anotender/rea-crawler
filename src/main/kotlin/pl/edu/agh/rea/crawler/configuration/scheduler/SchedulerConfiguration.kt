package pl.edu.agh.rea.crawler.configuration.scheduler

import kotlinx.coroutines.runBlocking
import org.springframework.context.annotation.Configuration
import pl.edu.agh.rea.crawler.configuration.properties.CrawlerConfigurationProperties
import pl.edu.agh.rea.crawler.domain.Crawler
import pl.edu.agh.rea.crawler.domain.VendorOffersCrawler
import pl.edu.agh.rea.crawler.domain.model.Offer
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.annotation.PostConstruct

@Configuration
class SchedulerConfiguration(private val crawlerConfigurationProperties: CrawlerConfigurationProperties) {

    private val threadPool = Executors.newScheduledThreadPool(crawlerConfigurationProperties.vendors.size)

    @PostConstruct
    private fun scheduleTasks() = crawlerConfigurationProperties
            .vendors
            .map(::VendorOffersCrawler)
            .forEach(this::scheduleCrawler)

    private fun scheduleCrawler(crawler: Crawler<List<Offer>>) {
        threadPool.schedule({
            val offers = runBlocking { crawler.fetch() }
            println(offers)
        }, 4, TimeUnit.HOURS)
    }

}