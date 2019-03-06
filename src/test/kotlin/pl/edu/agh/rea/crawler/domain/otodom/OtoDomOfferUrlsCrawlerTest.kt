package pl.edu.agh.rea.crawler.domain.otodom

import kotlinx.coroutines.runBlocking
import org.assertj.core.api.BDDAssertions.then
import org.junit.Test
import pl.edu.agh.rea.crawler.domain.BaseCrawlerTest
import pl.edu.agh.rea.crawler.domain.OfferUrlsCrawler

class OtoDomOfferUrlsCrawlerTest : BaseCrawlerTest("otodom") {

    @Test
    fun shouldReturnListOfUrlsForGivenPage() {
        //given
        val offerUrlsCrawler: OfferUrlsCrawler = getOfferUrlsCrawler()
        val offerUrlsPageUrl = getResourceUrl("offer_urls_page.html")

        //when
        val result = runBlocking { offerUrlsCrawler.fetch(offerUrlsPageUrl) }

        //then
        then(result)
                .isNotNull
                .hasSize(4)
                .containsExactly(
                        "https://www.otodom.pl/oferta/funkcjonalna-kawalerka-na-blisko-metra-slodowiec-ID3WVxi.html#14fcfe9f8e",
                        "https://www.otodom.pl/oferta/kawalerka-wysoki-standard-ul-pilotow-eng-ID3WVvo.html#14fcfe9f8e",
                        "https://www.otodom.pl/oferta/nowe-ladne-2-pok-woronicza-przy-mordorze-i-metrze-ID3WVv4.html#14fcfe9f8e",
                        "https://www.otodom.pl/oferta/luksusowe-piekne-mieszkanie-z-tarasem-na-mokotowie-ID3GqY8.html#14fcfe9f8e"
                )
    }

    @Test
    fun shouldReturnEmptyListForPageWithNoOffers() {
        //given
        val offerUrlsCrawler: OfferUrlsCrawler = getOfferUrlsCrawler()
        val offerUrlsPageUrl = getResourceUrl("offer_urls_empty_page.html")

        //when
        val result = runBlocking { offerUrlsCrawler.fetch(offerUrlsPageUrl) }

        //then
        then(result)
                .isNotNull
                .isEmpty()
    }

}