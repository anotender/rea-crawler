package pl.edu.agh.rea.crawler.configuration

import org.htmlcleaner.HtmlCleaner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Beans {

    @Bean
    fun htmlCleaner(): HtmlCleaner = HtmlCleaner()

}