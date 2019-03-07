package pl.edu.agh.rea.crawler.domain.otodom

import kotlinx.coroutines.runBlocking
import org.assertj.core.api.BDDAssertions.then
import org.junit.Test
import pl.edu.agh.rea.crawler.domain.BaseCrawlerTest
import pl.edu.agh.rea.crawler.domain.model.Offer

class OtoDomOfferCrawlerTest : BaseCrawlerTest("otodom") {

    @Test
    fun shouldReturnOfferWithCorrectValuesForGivenPage() {
        //given
        val offerCrawler = getOfferCrawler()
        val offerPageUrl = getResourceUrl("offer_page.html")

        //when
        val offer = runBlocking { offerCrawler.fetch(offerPageUrl) }

        //then
        then(offer).isEqualTo(Offer(
                offerPageUrl,
                "Kraków, Grzegórzki",
                "https://apollo-ireland.akamaized.net/v1/files/eyJmbiI6InBpZ3dnanFqdnB1cTMtQVBMIiwidyI6W3siZm4iOiJqMWozbzEzbTZiZ24xLUFQTCIsInMiOiIxNCIsInAiOiIxMCwtMTAiLCJhIjoiMCJ9XX0.Gfzf0C1fvKNFgd25jW1PONZ8bIe_PL2wRX8P7P3e4So/image;s=1280x1024;q=80",
                2100.0,
                41.0,
                2
        ))
    }

}