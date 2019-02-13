package pl.edu.agh.rea.crawler.domain

import kotlinx.coroutines.runBlocking
import org.assertj.core.api.BDDAssertions.then
import org.junit.Test

class MorizonOfferUrlsCrawlerTest : OfferUrlsCrawlerBaseTest() {

    override fun getOfferUrlXpath(): String {
        return "//*[@id=\"contentPage\"]/div/div/div/div/section/div/div/div/div/section/header/a/@href"
    }

    @Test
    fun shouldReturnListOfUrlsForGivenPage() {
        //given
        val offerUrlsCrawler = prepareOfferUrlCrawler("/morizon/offer_urls_page.html")

        //when
        val result = runBlocking { offerUrlsCrawler.fetch() }

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
        val offerUrlsCrawler = prepareOfferUrlCrawler("/morizon/offer_urls_empty_page.html")

        //when
        val result = runBlocking { offerUrlsCrawler.fetch() }

        //then
        then(result)
                .isNotNull
                .isEmpty()
    }

}