package pl.edu.agh.rea.crawler.domain.scraper.morizon

import pl.edu.agh.rea.crawler.domain.scraper.BaseOfferUrlsScraperTest

class MorizonOfferUrlsScraperTest : BaseOfferUrlsScraperTest("morizon") {

    override fun getExpectedUrls(): List<String> = listOf(
            "https://www.morizon.pl/oferta/sprzedaz-mieszkanie-krakow-mistrzejowice-73m2-mzn2032058697",
            "https://www.morizon.pl/oferta/sprzedaz-mieszkanie-krakow-grzegorzki-34m2-mzn2032058695",
            "https://www.morizon.pl/oferta/sprzedaz-mieszkanie-bytom-szombierki-zabrzanska-65m2-mzn2032057520"
    )

}