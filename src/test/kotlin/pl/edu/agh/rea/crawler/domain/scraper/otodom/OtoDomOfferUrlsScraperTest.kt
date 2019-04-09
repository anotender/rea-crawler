package pl.edu.agh.rea.crawler.domain.scraper.otodom

import pl.edu.agh.rea.crawler.domain.scraper.BaseOfferUrlsScraperTest

class OtoDomOfferUrlsScraperTest : BaseOfferUrlsScraperTest("otodom") {

    override fun getExpectedUrls(): List<String> = listOf(
            "https://www.otodom.pl/oferta/funkcjonalna-kawalerka-na-blisko-metra-slodowiec-ID3WVxi.html#14fcfe9f8e",
            "https://www.otodom.pl/oferta/kawalerka-wysoki-standard-ul-pilotow-eng-ID3WVvo.html#14fcfe9f8e",
            "https://www.otodom.pl/oferta/nowe-ladne-2-pok-woronicza-przy-mordorze-i-metrze-ID3WVv4.html#14fcfe9f8e",
            "https://www.otodom.pl/oferta/luksusowe-piekne-mieszkanie-z-tarasem-na-mokotowie-ID3GqY8.html#14fcfe9f8e"
    )

}