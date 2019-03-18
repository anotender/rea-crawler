package pl.edu.agh.rea.crawler.domain.morizon

import kotlinx.coroutines.runBlocking
import org.assertj.core.api.BDDAssertions.then
import org.junit.Test
import pl.edu.agh.rea.crawler.domain.BaseCrawlerTest
import pl.edu.agh.rea.crawler.domain.model.Offer

class MorizonOfferCrawlerTest : BaseCrawlerTest("morizon") {

    private val offerPageUrlToExpectedOfferMap: Map<String, Offer> = mapOf(
            getResourceUrl("offer_page1.html") to Offer(
                    getResourceUrl("offer_page1.html"),
                    "Warszawa, Wola",
                    "https://img2.staticmorizon.com.pl/thumbnail/aHR0cDovL2ltZy5tb3Jpem9uLnBsL3ptazc0OTMvOS96bWs3NDkzX21zXzI2NDglMjUyRjYxNjYlMjUyRk9NU18xLmpwZyN2PTFfMTE1ODMwMjAyMA==/832/468/2/kawalerka-na-sprzedaz-warszawa-wola-33-m-morizon-pl-3776.jpg",
                    290000.0,
                    32.5,
                    1
            ),
            getResourceUrl("offer_page2.html") to Offer(
                    getResourceUrl("offer_page2.html"),
                    "Warszawa, Śródmieście, Śródmieście Północne, Złota",
                    "https://img1.staticmorizon.com.pl/thumbnail/aHR0cDovL2ltZy5tb3Jpem9uLnBsL2c0ODYyLzEvZzQ4NjJfbXdfOTk5OTEzMDg2MDBfMS5qcGcjdj0xXzEyMTQyNzkxMzc=/832/468/2/mieszkanie-do-wynajecia-warszawa-srodmiescie-polnocne-62-m.jpg",
                    9250.0,
                    62.0,
                    2
            ),
            getResourceUrl("offer_page3.html") to Offer(
                    getResourceUrl("offer_page3.html"),
                    null,
                    "https://img3.staticmorizon.com.pl/thumbnail/aHR0cDovL21lZGlhLm1vcml6b24ucGwvaW1nL2Rld2Vsb3Blcnp5LzgzNDhfd2l6dWFsaXphY2phMS5qcGc=/1092/714/2/nowa-inwestycja-zachodnia-dabrowa-ul-zachodnia-morizon-pl.jpg",
                    null,
                    null,
                    null
            ),
            getResourceUrl("offer_page4.html") to Offer(
                    getResourceUrl("offer_page4.html"),
                    "Łódź, Śródmieście, Radwańska 51",
                    "https://img1.staticmorizon.com.pl/thumbnail/aHR0cDovL21lZGlhLmRiLm1vcml6b24ucGwvMC8xLzAvNy80LzE1MjgwNTEwNzQvMjMwMzg5OC5pbWc=/832/468/2/mieszkanie-na-sprzedaz-lodz-srodmiescie-41-m-morizon-pl-1579.jpg",
                    285000.0,
                    41.09,
                    2
            ),
            getResourceUrl("offer_page5.html") to Offer(
                    getResourceUrl("offer_page5.html"),
                    "Warszawski Zachodni, Ożarów Mazowiecki, Duchnice",
                    "https://staticmorizon.com.pl/images/placeholder/property-gallery.svg",
                    4999.0,
                    100.0,
                    null
            )
    )

    @Test
    fun shouldReturnOfferWithCorrectValuesForGivenPage() {
        for ((offerPageUrl, expectedOffer) in offerPageUrlToExpectedOfferMap) {
            //given
            val offerCrawler = getOfferCrawler()

            //when
            val offer = runBlocking { offerCrawler.fetch(offerPageUrl) }

            //then
            then(offer).isEqualTo(expectedOffer)
        }
    }

}