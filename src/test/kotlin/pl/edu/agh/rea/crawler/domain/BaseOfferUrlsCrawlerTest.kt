package pl.edu.agh.rea.crawler.domain

import kotlinx.coroutines.runBlocking
import org.assertj.core.api.BDDAssertions.then
import org.htmlcleaner.HtmlCleaner
import org.junit.Test

abstract class BaseOfferUrlsCrawlerTest(vendorName: String) : BaseCrawlerTest(vendorName) {

    private val offerUrlsCrawler: OfferUrlsCrawler = OfferUrlsCrawler(getVendorConfiguration(), HtmlCleaner())

    protected abstract fun getExpectedUrls(): List<String>

    @Test
    fun shouldReturnListOfUrlsForGivenPage() {
        //given
        val offerUrlsPageUrl = getResourceUrl("offer_urls_page.html")

        //when
        val result = runBlocking { offerUrlsCrawler.fetch(offerUrlsPageUrl) }

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
        val result = runBlocking { offerUrlsCrawler.fetch(offerUrlsPageUrl) }

        //then
        then(result)
                .isNotNull
                .isEmpty()
    }

}