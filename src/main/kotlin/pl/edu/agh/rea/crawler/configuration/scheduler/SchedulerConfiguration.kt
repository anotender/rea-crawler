package pl.edu.agh.rea.crawler.configuration.scheduler

import org.springframework.context.annotation.Configuration
import pl.edu.agh.rea.crawler.configuration.properties.CrawlerConfigurationProperties
import pl.edu.agh.rea.crawler.configuration.scheduler.job.VendorCrawlerJob
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.annotation.PostConstruct

@Configuration
class SchedulerConfiguration(private val crawlerConfigurationProperties: CrawlerConfigurationProperties) {

    private val threadPool = Executors.newScheduledThreadPool(crawlerConfigurationProperties.vendors.size)

    @PostConstruct
    private fun scheduleTasks() = crawlerConfigurationProperties
            .vendors
            .map(::VendorCrawlerJob)
            .forEach(this::scheduleJob)

    private fun scheduleJob(job: VendorCrawlerJob) {
        threadPool.schedule(job, 4, TimeUnit.HOURS)
    }

}