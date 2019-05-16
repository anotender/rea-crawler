package pl.edu.agh.rea.crawler.domain.scraper

import org.htmlcleaner.HtmlCleaner
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.w3c.dom.Document
import pl.edu.agh.rea.crawler.configuration.properties.VendorConfiguration
import pl.edu.agh.rea.crawler.domain.extensions.cleanToDocument
import pl.edu.agh.rea.crawler.domain.model.MarketType
import pl.edu.agh.rea.crawler.domain.model.Offer
import pl.edu.agh.rea.crawler.domain.scraper.extractor.Extractor
import pl.edu.agh.rea.crawler.domain.scraper.extractor.Field

@Component
class OfferScraper(private val vendorConfiguration: VendorConfiguration,
                   private val htmlCleaner: HtmlCleaner,
                   private val fieldExtractorMap: Map<Field, Extractor<Any>>) : Scraper<Offer> {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(OfferScraper::class.java)
    }

    override suspend fun scrap(urlToScrap: UrlToScrap): Offer {
        LOGGER.info("Scraping offer from url $urlToScrap")
        val offerPage = htmlCleaner.cleanToDocument(urlToScrap.url)
        val offer = buildOffer(urlToScrap, offerPage)
        LOGGER.info("Scraped offer from url $urlToScrap successfully")
        return offer
    }

    private fun buildOffer(urlToScrap: UrlToScrap, offerPage: Document): Offer = Offer(
            offerUrl = urlToScrap.url,
            address = getStringValue(Field.ADDRESS, offerPage),
            imageUrl = getStringValue(Field.IMAGE, offerPage),
            price = getDoubleValue(Field.PRICE, offerPage),
            area = getDoubleValue(Field.AREA, offerPage),
            numberOfRooms = getIntValue(Field.NUMBER_OF_ROOMS, offerPage),
            floor = getIntValue(Field.FLOOR, offerPage),
            yearOfConstruction = getIntValue(Field.YEAR_OF_CONSTRUCTION, offerPage),
            title = getStringValue(Field.TITLE, offerPage),
            marketType = getMarketType(Field.MARKET_TYPE, offerPage),
            offerType = urlToScrap.offerType,
            propertyType = urlToScrap.propertyType,
            vendor = vendorConfiguration.vendor
    )

    //FIXME czy można uniknąć rzutowania?
    private fun getStringValue(field: Field, offerPage: Document): String? {
        return fieldExtractorMap[field]?.extract(offerPage) as String?
    }

    private fun getDoubleValue(field: Field, offerPage: Document): Double? {
        return fieldExtractorMap[field]?.extract(offerPage) as Double?
    }

    private fun getIntValue(field: Field, offerPage: Document): Int? {
        return fieldExtractorMap[field]?.extract(offerPage) as Int?
    }

    private fun getMarketType(field: Field, offerPage: Document): MarketType? {
        return fieldExtractorMap[field]?.extract(offerPage) as MarketType?
    }

}