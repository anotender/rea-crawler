package pl.edu.agh.rea.crawler.configuration

import org.htmlcleaner.HtmlCleaner
import org.mapdb.DB
import org.mapdb.DBMaker
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.edu.agh.rea.crawler.configuration.properties.VendorConfiguration
import pl.edu.agh.rea.crawler.configuration.provider.ConfigurationReader
import pl.edu.agh.rea.crawler.domain.scraper.UrlToScrap

@Configuration
class CrawlingConfiguration {

    @Bean
    fun htmlCleaner(): HtmlCleaner = HtmlCleaner()

    @Bean
    fun visitedUrlsDb(@Value("\${VENDOR}") vendor: String): DB = DBMaker.fileDB("$vendor.db").make()

    @Bean
    fun offerUrlsToScrap(): MutableList<UrlToScrap> = mutableListOf()

    @Bean
    fun vendorConfiguration(configurationReader: ConfigurationReader): VendorConfiguration = configurationReader.readVendorConfiguration()

}