package pl.edu.agh.rea.crawler.domain.scraper

import kotlinx.coroutines.runBlocking
import org.assertj.core.api.JUnitBDDSoftAssertions
import org.htmlcleaner.HtmlCleaner
import org.junit.Rule
import org.junit.Test
import pl.edu.agh.rea.crawler.configuration.ScrapingConfiguration
import pl.edu.agh.rea.crawler.domain.model.Offer

abstract class BaseOfferScraperTest(vendorName: String) : BaseScraperTest(vendorName) {

    @Rule
    @JvmField
    val softly: JUnitBDDSoftAssertions = JUnitBDDSoftAssertions()

    private val offerScraper: OfferScraper = getOfferScraper()

    @Test
    fun shouldScrapOfferWithCorrectValuesFromGivenPage() {
        for ((offerPageUrl, expectedOffer) in getTestParameters()) {
            //given
            val urlToScrap = UrlToScrap(
                    offerPageUrl,
                    expectedOffer.offerType,
                    expectedOffer.propertyType
            )

            //when
            val offer = runBlocking { offerScraper.scrap(urlToScrap) }

            //then
            thenOfferHasExpectedValues(offer, expectedOffer)
        }
    }

    protected abstract fun getTestParameters(): Map<String, Offer>

    private fun thenOfferHasExpectedValues(actualOffer: Offer, expectedOffer: Offer) {
        softly.then(actualOffer.offerUrl)
                .`as`("offerUrl for ${expectedOffer.offerUrl} is not correct")
                .isEqualTo(expectedOffer.offerUrl)
        softly.then(actualOffer.title)
                .`as`("title for ${expectedOffer.offerUrl} is not correct")
                .isEqualTo(expectedOffer.title)
        softly.then(actualOffer.price)
                .`as`("price for ${expectedOffer.offerUrl} is not correct")
                .isEqualTo(expectedOffer.price)
        softly.then(actualOffer.imageUrl)
                .`as`("imageUrl for ${expectedOffer.offerUrl} is not correct")
                .isEqualTo(expectedOffer.imageUrl)
        softly.then(actualOffer.numberOfRooms)
                .`as`("numberOfRooms for ${expectedOffer.offerUrl} is not correct")
                .isEqualTo(expectedOffer.numberOfRooms)
        softly.then(actualOffer.address)
                .`as`("address for ${expectedOffer.offerUrl} is not correct")
                .isEqualTo(expectedOffer.address)
        softly.then(actualOffer.area)
                .`as`("area for ${expectedOffer.offerUrl} is not correct")
                .isEqualTo(expectedOffer.area)
        softly.then(actualOffer.floor)
                .`as`("floor for ${expectedOffer.offerUrl} is not correct")
                .isEqualTo(expectedOffer.floor)
        softly.then(actualOffer.yearOfConstruction)
                .`as`("yearOfConstruction for ${expectedOffer.offerUrl} is not correct")
                .isEqualTo(expectedOffer.yearOfConstruction)
        softly.then(actualOffer.offerType)
                .`as`("offerType for ${expectedOffer.offerUrl} is not correct")
                .isEqualTo(expectedOffer.offerType)
        softly.then(actualOffer.propertyType)
                .`as`("propertyType for ${expectedOffer.offerUrl} is not correct")
                .isEqualTo(expectedOffer.propertyType)
        softly.then(actualOffer.marketType)
                .`as`("propertyType for ${expectedOffer.offerUrl} is not correct")
                .isEqualTo(expectedOffer.marketType)
        softly.then(actualOffer.vendor)
                .`as`("vendor for ${expectedOffer.offerUrl} is not correct")
                .isEqualTo(expectedOffer.vendor)
    }

    private fun getOfferScraper(): OfferScraper {
        return OfferScraper(
                getVendorConfiguration(),
                HtmlCleaner(),
                ScrapingConfiguration().fieldExtractorMap(getVendorConfiguration())
        )
    }

}