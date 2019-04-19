package pl.edu.agh.rea.crawler.domain.scraper.domiporta

import pl.edu.agh.rea.crawler.domain.model.Offer
import pl.edu.agh.rea.crawler.domain.model.OfferType
import pl.edu.agh.rea.crawler.domain.model.PropertyType
import pl.edu.agh.rea.crawler.domain.model.Vendor
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
                    "Mieszkanie dwupokojowe na wynajem",
                    OfferType.RENT,
                    PropertyType.FLAT,
                    Vendor.DOMIPORTA
            ),
            getResourceUrl("offer_page2.html") to Offer(
                    getResourceUrl("offer_page2.html"),
                    "Warszawa, Bielany",
                    "https://galeria.domiporta.pl/pictures/big/12/3d/5b/5b3dbd1c9c7a9bbca64baec73079694d2e557f3c/wynajme-dom-warszawa-bielany.jpg",
                    13500.0,
                    720.0,
                    null,
                    "Dom na wynajem",
                    OfferType.RENT,
                    PropertyType.HOUSE,
                    Vendor.DOMIPORTA
            ),
            getResourceUrl("offer_page3.html") to Offer(
                    getResourceUrl("offer_page3.html"),
                    "Solec",
                    "https://galeria.domiporta.pl/pictures/big/15/0e/3d/3d0ee73666acbedad4a8aaa8d14453e202f6e0c0/wynajme-dom-solec.jpg",
                    6800.0,
                    200.0,
                    5,
                    "Dom na wynajem",
                    OfferType.RENT,
                    PropertyType.HOUSE,
                    Vendor.DOMIPORTA
            ),
            getResourceUrl("offer_page4.html") to Offer(
                    getResourceUrl("offer_page4.html"),
                    "Rzeszów, Słocina, Wieniawskiego",
                    "https://galeria.domiporta.pl/pictures/big/14/cd/b4/b4cd4b6b73141463d4f96a9fd53a814941ca7aef/sprzedam-mieszkanie-rzeszow-slocina.jpg",
                    184630.0,
                    41.49,
                    2,
                    "Mieszkanie dwupokojowe na sprzedaż",
                    OfferType.SELL,
                    PropertyType.FLAT,
                    Vendor.DOMIPORTA
            ),
            getResourceUrl("offer_page5.html") to Offer(
                    getResourceUrl("offer_page5.html"),
                    "Warszawa, Ursynów",
                    "https://galeria.domiporta.pl/pictures/big/17/79/d6/d6797ee6d761242e89ae9edef11dde320c51f231/wynajme-dom-warszawa-ursynow.jpg",
                    7900.0,
                    300.0,
                    7,
                    "Dom na wynajem",
                    OfferType.RENT,
                    PropertyType.HOUSE,
                    Vendor.DOMIPORTA
            ),
            getResourceUrl("offer_page6.html") to Offer(
                    getResourceUrl("offer_page6.html"),
                    "Łódź, Widzew, Mandarynkowa",
                    "https://galeria.domiporta.pl/pictures/big/14/69/f6/f669472159d06412a489106ae8c1516880f94363/wynajme-dom-lodz-widzew.jpg",
                    3800.0,
                    142.0,
                    4,
                    "Dom na wynajem",
                    OfferType.RENT,
                    PropertyType.HOUSE,
                    Vendor.DOMIPORTA
            ),
            getResourceUrl("offer_page7.html") to Offer(
                    getResourceUrl("offer_page7.html"),
                    "Murcia, San Javier",
                    "https://galeria.domiporta.pl/pictures/big/13/69/49/49693b41e68dd6146d0ee1c8fa7f6b8675ca91dc/sprzedam-dom-murcia.jpg",
                    1_631_473.0,
                    115.45,
                    4,
                    "Dom na sprzedaż",
                    OfferType.SELL,
                    PropertyType.HOUSE,
                    Vendor.DOMIPORTA
            )
    )

}