package pl.edu.agh.rea.crawler.domain.otodom

import pl.edu.agh.rea.crawler.domain.BaseOfferCrawlerTest
import pl.edu.agh.rea.crawler.domain.model.Offer

class OtoDomOfferCrawlerTest : BaseOfferCrawlerTest("otodom") {

    override fun getTestParameters(): Map<String, Offer> = mapOf(
            getResourceUrl("offer_page.html") to Offer(
                    getResourceUrl("offer_page.html"),
                    "Kraków, Grzegórzki",
                    "https://apollo-ireland.akamaized.net/v1/files/eyJmbiI6InBpZ3dnanFqdnB1cTMtQVBMIiwidyI6W3siZm4iOiJqMWozbzEzbTZiZ24xLUFQTCIsInMiOiIxNCIsInAiOiIxMCwtMTAiLCJhIjoiMCJ9XX0.Gfzf0C1fvKNFgd25jW1PONZ8bIe_PL2wRX8P7P3e4So/image;s=1280x1024;q=80",
                    2100.0,
                    41.0,
                    2
            )
    )

}