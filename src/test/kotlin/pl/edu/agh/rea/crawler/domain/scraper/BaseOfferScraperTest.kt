package pl.edu.agh.rea.crawler.domain.scraper

import kotlinx.coroutines.runBlocking
import org.assertj.core.api.JUnitBDDSoftAssertions
import org.htmlcleaner.HtmlCleaner
import org.junit.Rule
import org.junit.Test
import pl.edu.agh.rea.crawler.domain.model.Offer

abstract class BaseOfferScraperTest(vendorName: String) : BaseScraperTest(vendorName) {

    @Rule
    @JvmField
    val softly: JUnitBDDSoftAssertions = JUnitBDDSoftAssertions()

    private val offerScraper: OfferScraper = OfferScraper(getVendorConfiguration(), HtmlCleaner())

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
        softly.then(actualOffer.offerUrl).isEqualTo(expectedOffer.offerUrl)
        softly.then(actualOffer.title).isEqualTo(expectedOffer.title)
        softly.then(actualOffer.price).isEqualTo(expectedOffer.price)
        softly.then(actualOffer.imageUrl).isEqualTo(expectedOffer.imageUrl)
        softly.then(actualOffer.numberOfRooms).isEqualTo(expectedOffer.numberOfRooms)
        softly.then(actualOffer.address).isEqualTo(expectedOffer.address)
        softly.then(actualOffer.area).isEqualTo(expectedOffer.area)
        softly.then(actualOffer.offerType).isEqualTo(expectedOffer.offerType)
        softly.then(actualOffer.propertyType).isEqualTo(expectedOffer.propertyType)
        softly.then(actualOffer.vendor).isEqualTo(expectedOffer.vendor)
    }

}