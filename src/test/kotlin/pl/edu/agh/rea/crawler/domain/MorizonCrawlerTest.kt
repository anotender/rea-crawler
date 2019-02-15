package pl.edu.agh.rea.crawler.domain

import kotlinx.coroutines.runBlocking
import org.assertj.core.api.BDDAssertions.then
import org.junit.Test
import pl.edu.agh.rea.crawler.domain.model.Offer

class MorizonCrawlerTest : BaseCrawlerTest() {

    companion object {
        private const val VENDOR_NAME: String = "Morizon"
    }

    @Test
    fun shouldReturnOfferWithCorrectValuesForGivenPage() {
        //given
        val offerCrawler = getOfferCrawler(VENDOR_NAME)
        val offerPageUrl = this.javaClass.getResource("/morizon/offer_page.html").toString()

        //when
        val offer = runBlocking { offerCrawler.fetch(offerPageUrl) }

        //then
        then(offer).isEqualTo(Offer(
                offerPageUrl,
                "Warszawa, Wola",
                "https://img2.staticmorizon.com.pl/thumbnail/aHR0cDovL2ltZy5tb3Jpem9uLnBsL3ptazc0OTMvOS96bWs3NDkzX21zXzI2NDglMjUyRjYxNjYlMjUyRk9NU18xLmpwZyN2PTFfMTE1ODMwMjAyMA==/832/468/2/kawalerka-na-sprzedaz-warszawa-wola-33-m-morizon-pl-3776.jpg",
                "290 000&nbsp;zł",
                1
        ))
    }

}