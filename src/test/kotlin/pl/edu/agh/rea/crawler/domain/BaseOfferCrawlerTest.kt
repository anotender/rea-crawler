package pl.edu.agh.rea.crawler.domain

import kotlinx.coroutines.runBlocking
import org.assertj.core.api.JUnitSoftAssertions
import org.htmlcleaner.HtmlCleaner
import org.junit.Rule
import org.junit.Test
import pl.edu.agh.rea.crawler.domain.model.Offer

abstract class BaseOfferCrawlerTest(vendorName: String) : BaseCrawlerTest(vendorName) {

    @Rule
    @JvmField
    val softly: JUnitSoftAssertions = JUnitSoftAssertions()

    @Test
    fun shouldReturnOfferWithCorrectValuesForGivenPage() {
        for ((offerPageUrl, expectedOffer) in getTestParameters()) {
            //given
            val offerCrawler = getOfferCrawler()

            //when
            val offer = runBlocking { offerCrawler.fetch(offerPageUrl) }

            //then
            softly.assertThat(offer).isEqualTo(expectedOffer)
        }
    }

    protected abstract fun getTestParameters(): Map<String, Offer>

    private fun getOfferCrawler(): OfferCrawler = OfferCrawler(getVendorConfiguration(), HtmlCleaner())

}