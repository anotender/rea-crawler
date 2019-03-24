package pl.edu.agh.rea.crawler.configuration.scheduler

import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import pl.edu.agh.rea.crawler.configuration.properties.VendorConfiguration
import pl.edu.agh.rea.crawler.configuration.scheduler.job.OfferCrawlerJob
import pl.edu.agh.rea.crawler.configuration.scheduler.job.OfferUrlsCrawlerJob
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@Component
class SchedulerConfiguration(private val offerCrawlerJob: OfferCrawlerJob,
                             private val offerUrlsCrawlerJob: OfferUrlsCrawlerJob,
                             private val vendorConfiguration: VendorConfiguration) {

    private val offerCrawlerJobThreadExecutor = Executors.newSingleThreadScheduledExecutor()

    @EventListener(ApplicationReadyEvent::class)
    fun scheduleTasks() {
        Thread(offerUrlsCrawlerJob).start()
        offerCrawlerJobThreadExecutor.scheduleWithFixedDelay(
                offerCrawlerJob,
                vendorConfiguration.requestDelay,
                vendorConfiguration.requestDelay,
                TimeUnit.MILLISECONDS
        )
    }

}