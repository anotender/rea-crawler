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
            softly.assertThat(offer).isEqualTo(expectedOffer)
        }
    }

    protected abstract fun getTestParameters(): Map<String, Offer>

}