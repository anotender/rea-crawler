package pl.edu.agh.rea.crawler.configuration

import org.htmlcleaner.HtmlCleaner
import org.mapdb.DB
import org.mapdb.DBMaker
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Beans {

    @Bean
    fun htmlCleaner(): HtmlCleaner = HtmlCleaner()

    @Bean
    fun db(): DB = DBMaker.fileDB("database.db").make()

}