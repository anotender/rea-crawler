package pl.edu.agh.rea.crawler.domain

import kotlinx.coroutines.experimental.runBlocking
import org.assertj.core.api.BDDAssertions.then
import org.junit.Test
import pl.edu.agh.rea.crawler.domain.model.Offer

class MorizonOfferCrawlerTest : BaseOfferCrawlerTest() {

    override fun getVendorName(): String {
        return "Morizon"
    }

    @Test
    fun shouldReturnOfferWithCorrectValuesForGivenPage() {
        //given
        val offerPageUrl = this.javaClass.getResource("/morizon/offer_page.html")
        val offerCrawler = getOfferCrawler(offerPageUrl)

        //when
        val offer = runBlocking { offerCrawler.fetch() }

        //then
        then(offer).isEqualTo(Offer(
                offerPageUrl.toString(),
                "Warszawa, Wola",
                "https://img2.staticmorizon.com.pl/thumbnail/aHR0cDovL2ltZy5tb3Jpem9uLnBsL3ptazc0OTMvOS96bWs3NDkzX21zXzI2NDglMjUyRjYxNjYlMjUyRk9NU18xLmpwZyN2PTFfMTE1ODMwMjAyMA==/832/468/2/kawalerka-na-sprzedaz-warszawa-wola-33-m-morizon-pl-3776.jpg",
                "290 000&nbsp;zł",
                1
        ))
    }

}