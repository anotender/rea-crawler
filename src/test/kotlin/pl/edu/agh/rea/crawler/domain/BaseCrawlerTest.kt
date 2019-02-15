package pl.edu.agh.rea.crawler.domain

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import pl.edu.agh.rea.crawler.configuration.properties.CrawlerConfigurationProperties
import pl.edu.agh.rea.crawler.configuration.properties.VendorConfigurationProperties

@RunWith(SpringRunner::class)
@SpringBootTest
abstract class BaseCrawlerTest {

    @Autowired
    private lateinit var crawlerConfigurationProperties: CrawlerConfigurationProperties

    protected fun getVendorConfigurationPropertiesByVendorName(vendorName: String): VendorConfigurationProperties {
        return crawlerConfigurationProperties.vendors.first { it.name.equals(vendorName, true) }
    }

    protected fun getOfferCrawler(vendorName: String): OfferCrawler {
        return OfferCrawler(getVendorConfigurationPropertiesByVendorName(vendorName))
    }

    protected fun getOfferUrlsCrawler(vendorName: String): OfferUrlsCrawler {
        return OfferUrlsCrawler(getVendorConfigurationPropertiesByVendorName(vendorName));
    }

}