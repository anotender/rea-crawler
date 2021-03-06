package pl.edu.agh.rea.crawler.configuration.properties

import pl.edu.agh.rea.crawler.domain.model.OfferType
import pl.edu.agh.rea.crawler.domain.model.PropertyType
import pl.edu.agh.rea.crawler.domain.model.Vendor

data class VendorConfiguration(
        val vendor: Vendor,
        val baseUrl: String,
        val offerUrlXpath: String,
        val addressXpath: String,
        val imageXpath: String,
        val areaXpath: String,
        val priceXpath: String,
        val numberOfRoomsXpath: String,
        val floorXpath: String,
        val yearOfConstructionXpath: String,
        val marketTypeXpath: String?,
        val titleXpath: String,
        val pageParam: String,
        val pagesToVisit: Int,
        val concurrentRequestsCount: Int,
        val requestDelay: Long,
        val pages: List<PageConfiguration>)

data class PageConfiguration(
        val offerType: OfferType,
        val propertyType: PropertyType,
        val url: String
)