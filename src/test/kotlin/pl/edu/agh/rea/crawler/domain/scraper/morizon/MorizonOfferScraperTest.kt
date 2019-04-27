package pl.edu.agh.rea.crawler.domain.scraper.morizon

import pl.edu.agh.rea.crawler.domain.model.Offer
import pl.edu.agh.rea.crawler.domain.model.OfferType
import pl.edu.agh.rea.crawler.domain.model.PropertyType
import pl.edu.agh.rea.crawler.domain.model.Vendor
import pl.edu.agh.rea.crawler.domain.scraper.BaseOfferScraperTest

class MorizonOfferScraperTest : BaseOfferScraperTest("morizon") {

    override fun getTestParameters(): Map<String, Offer> = mapOf(
            getResourceUrl("offer_page1.html") to Offer(
                    getResourceUrl("offer_page1.html"),
                    "Warszawa, Wola",
                    "https://img2.staticmorizon.com.pl/thumbnail/aHR0cDovL2ltZy5tb3Jpem9uLnBsL3ptazc0OTMvOS96bWs3NDkzX21zXzI2NDglMjUyRjYxNjYlMjUyRk9NU18xLmpwZyN2PTFfMTE1ODMwMjAyMA==/832/468/2/kawalerka-na-sprzedaz-warszawa-wola-33-m-morizon-pl-3776.jpg",
                    290000.0,
                    32.5,
                    1,
                    2,
                    1936,
                    "Warszawa, Wola",
                    OfferType.RENT,
                    PropertyType.FLAT,
                    Vendor.MORIZON
            ),
            getResourceUrl("offer_page2.html") to Offer(
                    getResourceUrl("offer_page2.html"),
                    "Warszawa, Śródmieście, Śródmieście Północne, Złota",
                    "https://img1.staticmorizon.com.pl/thumbnail/aHR0cDovL2ltZy5tb3Jpem9uLnBsL2c0ODYyLzEvZzQ4NjJfbXdfOTk5OTEzMDg2MDBfMS5qcGcjdj0xXzEyMTQyNzkxMzc=/832/468/2/mieszkanie-do-wynajecia-warszawa-srodmiescie-polnocne-62-m.jpg",
                    9250.0,
                    62.0,
                    2,
                    12,
                    2017,
                    "Warszawa, Śródmieście, Śródmieście Północne, Złota",
                    OfferType.RENT,
                    PropertyType.HOUSE,
                    Vendor.MORIZON
            ),
            getResourceUrl("offer_page3.html") to Offer(
                    getResourceUrl("offer_page3.html"),
                    null,
                    "https://img3.staticmorizon.com.pl/thumbnail/aHR0cDovL21lZGlhLm1vcml6b24ucGwvaW1nL2Rld2Vsb3Blcnp5LzgzNDhfd2l6dWFsaXphY2phMS5qcGc=/1092/714/2/nowa-inwestycja-zachodnia-dabrowa-ul-zachodnia-morizon-pl.jpg",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    OfferType.SELL,
                    PropertyType.FLAT,
                    Vendor.MORIZON
            ),
            getResourceUrl("offer_page4.html") to Offer(
                    getResourceUrl("offer_page4.html"),
                    "Łódź, Śródmieście, Radwańska 51",
                    "https://img1.staticmorizon.com.pl/thumbnail/aHR0cDovL21lZGlhLmRiLm1vcml6b24ucGwvMC8xLzAvNy80LzE1MjgwNTEwNzQvMjMwMzg5OC5pbWc=/832/468/2/mieszkanie-na-sprzedaz-lodz-srodmiescie-41-m-morizon-pl-1579.jpg",
                    285000.0,
                    41.09,
                    2,
                    3,
                    null,
                    "Łódź, Śródmieście, Radwańska 51",
                    OfferType.RENT,
                    PropertyType.FLAT,
                    Vendor.MORIZON
            ),
            getResourceUrl("offer_page5.html") to Offer(
                    getResourceUrl("offer_page5.html"),
                    "Warszawski Zachodni, Ożarów Mazowiecki, Duchnice",
                    "https://staticmorizon.com.pl/images/placeholder/property-gallery.svg",
                    4999.0,
                    100.0,
                    null,
                    null,
                    null,
                    "Warszawski Zachodni, Ożarów Mazowiecki, Duchnice",
                    OfferType.SELL,
                    PropertyType.FLAT,
                    Vendor.MORIZON
            ),
            getResourceUrl("offer_page6.html") to Offer(
                    getResourceUrl("offer_page6.html"),
                    "Kraków, Stare Miasto, al. Juliusza Słowackiego",
                    "https://img1.staticmorizon.com.pl/thumbnail/aHR0cDovL2ltZy5tb3Jpem9uLnBsL2FwYXJ0aDEvMi9hcGFydGgxX213Xzc1JTI1MkY3NTUxJTI1MkZPTVdfMS5qcGcjdj0xXzEyMzI3OTYzODU=/832/468/2/mieszkanie-do-wynajecia-krakow-stare-miasto-50-m-morizon-pl.jpg",
                    1900.0,
                    50.0,
                    2,
                    4,
                    1925,
                    "Kraków, Stare Miasto, al. Juliusza Słowackiego",
                    OfferType.RENT,
                    PropertyType.HOUSE,
                    Vendor.MORIZON
            ),
            getResourceUrl("offer_page7.html") to Offer(
                    getResourceUrl("offer_page7.html"),
                    "Gdańsk, Morena, Piekarnicza",
                    "https://img2.staticmorizon.com.pl/thumbnail/aHR0cDovL2ltZy5tb3Jpem9uLnBsL2hvbWVicm9rZXIvNi9ob21lYnJva2VyX213XzM4NzgxN18xLmpwZyN2PTFfMTIzMjc5NjE0OA==/832/468/2/mieszkanie-do-wynajecia-gdansk-piecki-migowo-53-m-morizon-pl.jpg",
                    1700.0,
                    52.5,
                    2,
                    null,
                    2004,
                    "Gdańsk, Morena, Piekarnicza",
                    OfferType.SELL,
                    PropertyType.HOUSE,
                    Vendor.MORIZON
            ),
            getResourceUrl("offer_page8.html") to Offer(
                    getResourceUrl("offer_page8.html"),
                    "Warszawa, Praga-Północ, Warszawa, Praga-Północ, Szwedzka",
                    "https://img3.staticmorizon.com.pl/thumbnail/aHR0cDovL2ltZy5tb3Jpem9uLnBsL2cxNTQyNy9lL2cxNTQyN19tc18xOTY3MDhfMS5qcGcjdj0xXzEyNTYzODk4NjQ=/832/468/2/mieszkanie-na-sprzedaz-warszawa-praga-polnoc-38-m-morizon-pl.jpg",
                    453002.0,
                    38.39,
                    2,
                    3,
                    2020,
                    "Warszawa, Praga-Północ, Warszawa, Praga-Północ, Szwedzka",
                    OfferType.SELL,
                    PropertyType.HOUSE,
                    Vendor.MORIZON
            ),
            getResourceUrl("offer_page9.html") to Offer(
                    getResourceUrl("offer_page9.html"),
                    "Warszawa, Ochota, Rakowiec, Leżajska",
                    "https://img3.staticmorizon.com.pl/thumbnail/aHR0cDovL2ltZy5tb3Jpem9uLnBsL3N6dWxlY2tpLzIvc3p1bGVja2lfbXNfMzNfNS5qcGcjdj0xXzEyMzc0MTIzNjI=/832/468/2/mieszkanie-na-sprzedaz-warszawa-rakowiec-84-m-morizon-pl.jpg",
                    720000.0,
                    84.4,
                    4,
                    0,
                    1993,
                    "Warszawa, Ochota, Rakowiec, Leżajska",
                    OfferType.SELL,
                    PropertyType.FLAT,
                    Vendor.MORIZON
            )
    )

}