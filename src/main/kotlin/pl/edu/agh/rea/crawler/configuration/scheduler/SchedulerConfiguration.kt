package pl.edu.agh.rea.crawler.configuration.scheduler

import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import pl.edu.agh.rea.crawler.configuration.scheduler.job.VendorCrawlerJob

@Component
class SchedulerConfiguration(private val vendorCrawlerJob: VendorCrawlerJob) {

    @EventListener(ApplicationReadyEvent::class)
    fun scheduleTask() {
        vendorCrawlerJob.run()
    }

}