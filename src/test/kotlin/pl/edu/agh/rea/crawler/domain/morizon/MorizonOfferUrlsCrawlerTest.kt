package pl.edu.agh.rea.crawler.domain.morizon

import kotlinx.coroutines.runBlocking
import org.assertj.core.api.BDDAssertions.then
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import pl.edu.agh.rea.crawler.domain.BaseCrawlerTest
import pl.edu.agh.rea.crawler.domain.OfferUrlsCrawler

@RunWith(MockitoJUnitRunner::class)
class MorizonOfferUrlsCrawlerTest : BaseCrawlerTest("morizon") {

    @Test
    fun shouldReturnListOfUrlsForGivenPage() {
        //given
        val offerUrlsCrawler: OfferUrlsCrawler = getOfferUrlsCrawler()
        val offerUrlsPageUrl = getResourceUrl("/morizon/offer_urls_page.html")

        //when
        val result = runBlocking { offerUrlsCrawler.fetch(offerUrlsPageUrl) }

        //then
        then(result)
                .isNotNull
                .hasSize(3)
                .containsExactly(
                        "https://www.morizon.pl/oferta/sprzedaz-mieszkanie-krakow-mistrzejowice-73m2-mzn2032058697",
                        "https://www.morizon.pl/oferta/sprzedaz-mieszkanie-krakow-grzegorzki-34m2-mzn2032058695",
                        "https://www.morizon.pl/oferta/sprzedaz-mieszkanie-bytom-szombierki-zabrzanska-65m2-mzn2032057520"
                )
    }

    @Test
    fun shouldReturnEmptyListForPageWithNoOffers() {
        //given
        val offerUrlsCrawler: OfferUrlsCrawler = getOfferUrlsCrawler()
        val offerUrlsPageUrl = getResourceUrl("/morizon/offer_urls_empty_page.html")

        //when
        val result = runBlocking { offerUrlsCrawler.fetch(offerUrlsPageUrl) }

        //then
        then(result)
                .isNotNull
                .isEmpty()
    }

}