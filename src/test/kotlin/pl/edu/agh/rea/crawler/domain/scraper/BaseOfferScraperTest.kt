package pl.edu.agh.rea.crawler.domain.scraper

import kotlinx.coroutines.runBlocking
import org.assertj.core.api.JUnitSoftAssertions
import org.htmlcleaner.HtmlCleaner
import org.junit.Rule
import org.junit.Test
import pl.edu.agh.rea.crawler.domain.model.Offer

abstract class BaseOfferScraperTest(vendorName: String) : BaseScraperTest(vendorName) {

    @Rule
    @JvmField
    val softly: JUnitSoftAssertions = JUnitSoftAssertions()

    private val offerScraper: OfferScraper = OfferScraper(getVendorConfiguration(), HtmlCleaner())

    @Test
    fun shouldScrapOfferWithCorrectValuesFromGivenPage() {
        for ((offerPageUrl, expectedOffer) in getTestParameters()) {
            //when
            val offer = runBlocking { offerScraper.scrap(offerPageUrl) }

            //then
            thenOfferHasExpectedValues(offer, expectedOffer)
        }
    }

    protected abstract fun getTestParameters(): Map<String, Offer>

    private fun thenOfferHasExpectedValues(actualOffer: Offer, expectedOffer: Offer) {
        softly.assertThat(actualOffer.offerUrl).isEqualTo(expectedOffer.offerUrl)
        softly.assertThat(actualOffer.title).isEqualTo(expectedOffer.title)
        softly.assertThat(actualOffer.price).isEqualTo(expectedOffer.price)
        softly.assertThat(actualOffer.imageUrl).isEqualTo(expectedOffer.imageUrl)
        softly.assertThat(actualOffer.numberOfRooms).isEqualTo(expectedOffer.numberOfRooms)
        softly.assertThat(actualOffer.address).isEqualTo(expectedOffer.address)
        softly.assertThat(actualOffer.area).isEqualTo(expectedOffer.area)
    }

}