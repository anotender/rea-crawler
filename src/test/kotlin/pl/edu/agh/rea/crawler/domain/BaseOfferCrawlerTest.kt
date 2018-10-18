package pl.edu.agh.rea.crawler.domain

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import pl.edu.agh.rea.crawler.configuration.properties.CrawlerConfigurationProperties
import pl.edu.agh.rea.crawler.configuration.properties.VendorConfigurationProperties
import java.net.URL

@RunWith(SpringRunner::class)
@SpringBootTest
abstract class BaseOfferCrawlerTest {

    @Autowired
    private lateinit var crawlerConfigurationProperties: CrawlerConfigurationProperties

    abstract fun getVendorName(): String

    protected fun getVendorConfigurationProperties(): VendorConfigurationProperties {
        return crawlerConfigurationProperties.vendors.first { it.name.equals(getVendorName(), true) }
    }

    protected fun getOfferCrawler(testFileUrl: URL): OfferCrawler {
        return OfferCrawler(getVendorConfigurationProperties(), testFileUrl)
    }

}