package pl.edu.agh.rea.crawler.domain

import kotlinx.coroutines.runBlocking
import org.assertj.core.api.BDDAssertions.then
import org.htmlcleaner.HtmlCleaner
import org.junit.Test
import pl.edu.agh.rea.crawler.domain.model.Offer

abstract class BaseOfferCrawlerTest(vendorName: String) : BaseCrawlerTest(vendorName) {

    @Test
    fun shouldReturnOfferWithCorrectValuesForGivenPage() {
        for ((offerPageUrl, expectedOffer) in getTestParameters()) {
            //given
            val offerCrawler = getOfferCrawler()

            //when
            val offer = runBlocking { offerCrawler.fetch(offerPageUrl) }

            //then
            then(offer).isEqualTo(expectedOffer)
        }
    }

    protected abstract fun getTestParameters(): Map<String, Offer>

    private fun getOfferCrawler(): OfferCrawler = OfferCrawler(getConfigurationProvider(), HtmlCleaner())

}