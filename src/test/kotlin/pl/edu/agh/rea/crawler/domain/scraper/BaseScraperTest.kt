package pl.edu.agh.rea.crawler.domain.scraper

import org.springframework.core.io.ClassRelativeResourceLoader
import pl.edu.agh.rea.crawler.configuration.properties.VendorConfiguration
import pl.edu.agh.rea.crawler.configuration.provider.ConfigurationReader

abstract class BaseScraperTest(private val vendorName: String) {

    protected fun getResourceUrl(resourceName: String): String = this.javaClass.getResource("/$vendorName/$resourceName").toString()

    protected fun getVendorConfiguration(): VendorConfiguration = ConfigurationReader(vendorName, ClassRelativeResourceLoader(BaseScraperTest::class.java)).readVendorConfiguration()

}