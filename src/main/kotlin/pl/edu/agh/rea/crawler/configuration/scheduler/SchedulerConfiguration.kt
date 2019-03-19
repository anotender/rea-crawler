package pl.edu.agh.rea.crawler.configuration.scheduler

import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import pl.edu.agh.rea.crawler.configuration.scheduler.job.CrawlerJob

@Component
class SchedulerConfiguration(private val crawlerJobs: List<CrawlerJob>) {

    @EventListener(ApplicationReadyEvent::class)
    fun scheduleTasks() {
        crawlerJobs.map { Thread(it) }.forEach { it.start() }
    }

}