package pl.edu.agh.rea.crawler.domain.scraper

import kotlinx.coroutines.runBlocking
import org.assertj.core.api.BDDAssertions.then
import org.htmlcleaner.HtmlCleaner
import org.junit.Test

abstract class BaseOfferUrlsScraperTest(vendorName: String) : BaseScraperTest(vendorName) {

    private val offerUrlsScraper: OfferUrlsScraper = OfferUrlsScraper(getVendorConfiguration(), HtmlCleaner())

    protected abstract fun getExpectedUrls(): List<String>

    @Test
    fun shouldReturnListOfUrlsForGivenPage() {
        //given
        val offerUrlsPageUrl = getResourceUrl("offer_urls_page.html")

        //when
        val result = runBlocking { offerUrlsScraper.scrap(offerUrlsPageUrl) }

        //then
        then(result)
                .isNotNull
                .hasSameSizeAs(getExpectedUrls())
                .hasSameElementsAs(getExpectedUrls())
    }

    @Test
    fun shouldReturnEmptyListForPageWithNoOffers() {
        //given
        val offerUrlsPageUrl = getResourceUrl("offer_urls_empty_page.html")

        //when
        val result = runBlocking { offerUrlsScraper.scrap(offerUrlsPageUrl) }

        //then
        then(result)
                .isNotNull
                .isEmpty()
    }

}