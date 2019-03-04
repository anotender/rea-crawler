package pl.edu.agh.rea.crawler.configuration.scheduler

import org.springframework.context.annotation.Configuration
import pl.edu.agh.rea.crawler.configuration.scheduler.job.VendorCrawlerJob
import javax.annotation.PostConstruct

@Configuration
class SchedulerConfiguration(private val vendorCrawlerJob: VendorCrawlerJob) {

    @PostConstruct
    private fun scheduleTask() {
        vendorCrawlerJob.run()
    }

}