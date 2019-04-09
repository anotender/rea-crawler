package pl.edu.agh.rea.crawler.configuration.scheduler

import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import pl.edu.agh.rea.crawler.configuration.properties.VendorConfiguration
import pl.edu.agh.rea.crawler.domain.crawler.OfferCrawler
import pl.edu.agh.rea.crawler.domain.crawler.OfferUrlsCrawler
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@Component
class SchedulerConfiguration(private val offerCrawler: OfferCrawler,
                             private val offerUrlsCrawler: OfferUrlsCrawler,
                             private val vendorConfiguration: VendorConfiguration) {

    private val offerCrawlerThreadExecutor = Executors.newSingleThreadScheduledExecutor()

    @EventListener(ApplicationReadyEvent::class)
    fun scheduleTasks() {
        Thread(offerUrlsCrawler).start()
        offerCrawlerThreadExecutor.scheduleWithFixedDelay(
                offerCrawler,
                vendorConfiguration.requestDelay,
                vendorConfiguration.requestDelay,
                TimeUnit.MILLISECONDS
        )
    }

}