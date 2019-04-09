package pl.edu.agh.rea.crawler.domain.scraper

import kotlinx.coroutines.runBlocking
import org.assertj.core.api.BDDAssertions.then
import org.htmlcleaner.HtmlCleaner
import org.junit.Test
import pl.edu.agh.rea.crawler.domain.model.OfferType
import pl.edu.agh.rea.crawler.domain.model.PropertyType

abstract class BaseOfferUrlsScraperTest(vendorName: String) : BaseScraperTest(vendorName) {

    private val offerUrlsScraper: OfferUrlsScraper = OfferUrlsScraper(getVendorConfiguration(), HtmlCleaner())

    protected abstract fun getExpectedUrls(): List<String>

    @Test
    fun shouldReturnListOfUrlsForGivenPage() {
        //given
        val urlToScrap = UrlToScrap(
                getResourceUrl("offer_urls_page.html"),
                OfferType.SELL,
                PropertyType.HOUSE
        )

        //when
        val result = runBlocking { offerUrlsScraper.scrap(urlToScrap) }

        //then
        then(result).isNotNull
        then(result.map(UrlToScrap::url))
                .hasSameSizeAs(getExpectedUrls())
                .hasSameElementsAs(getExpectedUrls())
    }

    @Test
    fun shouldReturnEmptyListForPageWithNoOffers() {
        //given
        val urlToScrap = UrlToScrap(
                getResourceUrl("offer_urls_empty_page.html"),
                OfferType.SELL,
                PropertyType.HOUSE
        )

        //when
        val result = runBlocking { offerUrlsScraper.scrap(urlToScrap) }

        //then
        then(result)
                .isNotNull
                .isEmpty()
    }

}