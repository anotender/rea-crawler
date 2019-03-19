package pl.edu.agh.rea.crawler.domain

import pl.edu.agh.rea.crawler.configuration.provider.ConfigurationProvider

abstract class BaseCrawlerTest(private val vendorName: String) {

    protected fun getResourceUrl(resourceName: String): String = this.javaClass.getResource("/$vendorName/$resourceName").toString()

    protected fun getConfigurationProvider(): ConfigurationProvider = ConfigurationProvider(vendorName)

}