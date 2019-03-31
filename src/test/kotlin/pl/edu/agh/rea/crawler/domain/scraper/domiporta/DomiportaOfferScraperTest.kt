package pl.edu.agh.rea.crawler.domain.scraper.domiporta

import pl.edu.agh.rea.crawler.domain.model.Offer
import pl.edu.agh.rea.crawler.domain.scraper.BaseOfferScraperTest

class DomiportaOfferScraperTest : BaseOfferScraperTest("domiporta") {

    override fun getTestParameters(): Map<String, Offer> = mapOf(
            getResourceUrl("offer_page1.html") to Offer(
                    getResourceUrl("offer_page1.html"),
                    "Wrocław, Krzyki, Opolska",
                    "https://galeria.domiporta.pl/pictures/big/11/3f/22/223fac96484cfce41f0a29bc4ef99d5f1a07a3ae/wynajme-mieszkanie-wroclaw-krzyki.jpg",
                    1100.0,
                    45.0,
                    2,
                    "Mieszkanie dwupokojowe na wynajem"
            ),
            getResourceUrl("offer_page2.html") to Offer(
                    getResourceUrl("offer_page2.html"),
                    "Warszawa, Bielany",
                    "https://galeria.domiporta.pl/pictures/big/12/3d/5b/5b3dbd1c9c7a9bbca64baec73079694d2e557f3c/wynajme-dom-warszawa-bielany.jpg",
                    13500.0,
                    720.0,
                    null,
                    "Dom na wynajem"
            ),
            getResourceUrl("offer_page3.html") to Offer(
                    getResourceUrl("offer_page3.html"),
                    "Solec",
                    "https://galeria.domiporta.pl/pictures/big/15/0e/3d/3d0ee73666acbedad4a8aaa8d14453e202f6e0c0/wynajme-dom-solec.jpg",
                    6800.0,
                    200.0,
                    5,
                    "Dom na wynajem"
            ),
            getResourceUrl("offer_page4.html") to Offer(
                    getResourceUrl("offer_page4.html"),
                    "Rzeszów, Słocina, Wieniawskiego",
                    "https://galeria.domiporta.pl/pictures/big/14/cd/b4/b4cd4b6b73141463d4f96a9fd53a814941ca7aef/sprzedam-mieszkanie-rzeszow-slocina.jpg",
                    184630.0,
                    41.49,
                    2,
                    "Mieszkanie dwupokojowe na sprzedaż"
            )
    )

}