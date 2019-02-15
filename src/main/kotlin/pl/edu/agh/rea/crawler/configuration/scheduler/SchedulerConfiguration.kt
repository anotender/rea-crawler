package pl.edu.agh.rea.crawler.configuration.scheduler

import kotlinx.coroutines.runBlocking
import org.springframework.context.annotation.Configuration
import pl.edu.agh.rea.crawler.configuration.properties.CrawlerConfigurationProperties
import pl.edu.agh.rea.crawler.configuration.properties.VendorConfigurationProperties
import pl.edu.agh.rea.crawler.domain.VendorOffersCrawler
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.annotation.PostConstruct

@Configuration
class SchedulerConfiguration(private val crawlerConfigurationProperties: CrawlerConfigurationProperties) {

    private val threadPool = Executors.newScheduledThreadPool(crawlerConfigurationProperties.vendors.size)

    @PostConstruct
    private fun scheduleTasks() = crawlerConfigurationProperties
            .vendors
            .forEach(this::scheduleCrawler)

    private fun scheduleCrawler(vendorConfigurationProperties: VendorConfigurationProperties) {
        threadPool.schedule({
            val crawler = VendorOffersCrawler(vendorConfigurationProperties)
            val offers = runBlocking { crawler.fetch(vendorConfigurationProperties.baseUrl) }
            println(offers)
        }, 4, TimeUnit.HOURS)
    }

}