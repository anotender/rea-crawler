package pl.edu.agh.rea.crawler.configuration.properties

import pl.edu.agh.rea.crawler.domain.model.OfferType
import pl.edu.agh.rea.crawler.domain.model.PropertyType

data class VendorConfigurationProperties(
        val name: String,
        val baseUrl: String,
        val offerUrlXpath: String,
        val addressXpath: String,
        val imageXpath: String,
        val areaXpath: String,
        val priceXpath: String,
        val numberOfRoomsXpath: String,
        val pageParam: String,
        val pagesToVisit: Int,
        val concurrentRequestsCount: Int,
        val requestDelay: Long,
        val pages: List<PageConfigurationProperties>)

data class PageConfigurationProperties(
        val offerType: OfferType,
        val propertyType: PropertyType,
        val url: String
)