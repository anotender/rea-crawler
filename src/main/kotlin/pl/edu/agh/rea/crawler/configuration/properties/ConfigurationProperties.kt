package pl.edu.agh.rea.crawler.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "crawler")
class CrawlerConfigurationProperties {

    lateinit var vendors: List<VendorConfigurationProperties>

}

class VendorConfigurationProperties {

    lateinit var name: String

    lateinit var baseUrl: String

    lateinit var offerUrlXpath: String

    lateinit var addressXpath: String

    lateinit var imageXpath: String

    lateinit var areaXpath: String

    lateinit var priceXpath: String

    lateinit var numberOfRoomsXpath: String

    lateinit var pageParam: String

    lateinit var pagesToVisit: Number

    lateinit var concurrentRequestsCount: Number

    lateinit var requestDelay: Number

    lateinit var pages: List<String>

}