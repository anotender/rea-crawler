package pl.edu.agh.rea.crawler.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.w3c.dom.Document
import pl.edu.agh.rea.crawler.configuration.properties.VendorConfiguration
import pl.edu.agh.rea.crawler.domain.model.MarketType
import pl.edu.agh.rea.crawler.domain.scraper.extractor.*

@Configuration
class ScrapingConfiguration {

    @Bean
    fun fieldExtractorMap(vendorConfiguration: VendorConfiguration): Map<Field, Extractor<Any>> {
        return mapOf(
                Field.TITLE to stringValueExtractor(vendorConfiguration.titleXpath),
                Field.ADDRESS to stringValueExtractor(vendorConfiguration.addressXpath),
                Field.IMAGE to stringValueExtractor(vendorConfiguration.imageXpath),
                Field.AREA to doubleValueExtractor(vendorConfiguration.areaXpath),
                Field.NUMBER_OF_ROOMS to intValueExtractor(vendorConfiguration.numberOfRoomsXpath),
                Field.YEAR_OF_CONSTRUCTION to intValueExtractor(vendorConfiguration.yearOfConstructionXpath),
                Field.PRICE to priceExtractor(vendorConfiguration.priceXpath),
                Field.FLOOR to floorExtractor(vendorConfiguration.floorXpath),
                Field.MARKET_TYPE to marketTypeExtractor(vendorConfiguration.marketTypeXpath)
        )
    }

    private fun marketTypeExtractor(xPath: String?): Extractor<MarketType> {
        return when (xPath) {
            null -> emptyExtractor()
            else -> MarketTypeExtractor(stringValueExtractor(xPath))
        }
    }

    private fun floorExtractor(xPath: String): Extractor<Int> {
        return FloorExtractor(stringValueExtractor(xPath))
    }

    private fun priceExtractor(xPath: String): Extractor<Double> {
        return PriceExtractor(stringValueExtractor(xPath))
    }

    private fun intValueExtractor(xPath: String): Extractor<Int> {
        return IntValueExtractor(stringValueExtractor(xPath))
    }

    private fun doubleValueExtractor(xPath: String): Extractor<Double> {
        return DoubleValueExtractor(stringValueExtractor(xPath))
    }

    private fun stringValueExtractor(xPath: String): Extractor<String> {
        return StringValueExtractor(xPath)
    }

    private fun <T> emptyExtractor(): Extractor<T> {
        return object : Extractor<T> {
            override fun extract(document: Document): T? {
                return null
            }
        }
    }

}