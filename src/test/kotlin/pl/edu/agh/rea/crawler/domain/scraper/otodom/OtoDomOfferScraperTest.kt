package pl.edu.agh.rea.crawler.domain.scraper.otodom

import pl.edu.agh.rea.crawler.domain.model.Offer
import pl.edu.agh.rea.crawler.domain.model.OfferType
import pl.edu.agh.rea.crawler.domain.model.PropertyType
import pl.edu.agh.rea.crawler.domain.model.Vendor
import pl.edu.agh.rea.crawler.domain.scraper.BaseOfferScraperTest

class OtoDomOfferScraperTest : BaseOfferScraperTest("otodom") {

    override fun getTestParameters(): Map<String, Offer> = mapOf(
            getResourceUrl("offer_page1.html") to Offer(
                    getResourceUrl("offer_page1.html"),
                    "Warszawa, Ursynów",
                    "https://apollo-ireland.akamaized.net/v1/files/eyJmbiI6InB5NW10YTVza25qejEtQVBMIiwidyI6W3siZm4iOiJqMWozbzEzbTZiZ24xLUFQTCIsInMiOiIxNCIsInAiOiIxMCwtMTAiLCJhIjoiMCJ9XX0.G5elGQN3dE4m3i9nPbbbMBAVjONcgAIyG3ybKlo1Xe0/image;s=1280x1024;q=80",
                    7900.0,
                    212.0,
                    5,
                    "Nowo wykończony dom w Dawidach Bankowych",
                    OfferType.RENT,
                    PropertyType.FLAT,
                    Vendor.OTODOM
            ),
            getResourceUrl("offer_page2.html") to Offer(
                    getResourceUrl("offer_page2.html"),
                    "Wrocław, Fabryczna",
                    "https://apollo-ireland.akamaized.net/v1/files/eyJmbiI6ImxpankycWxmc2Z4cTMtQVBMIiwidyI6W3siZm4iOiJqMWozbzEzbTZiZ24xLUFQTCIsInMiOiIxNCIsInAiOiIxMCwtMTAiLCJhIjoiMCJ9XX0.kBVdFePgRNvDPw6xI1hbDcGj_s6LXuyHbY-pX1x4L9E/image;s=1280x1024;q=80",
                    7000.0,
                    150.0,
                    5,
                    "Dom dla Pracowników/na firmę /ok.Kołobrzeskiej",
                    OfferType.RENT,
                    PropertyType.HOUSE,
                    Vendor.OTODOM
            ),
            getResourceUrl("offer_page3.html") to Offer(
                    getResourceUrl("offer_page3.html"),
                    "Gdańsk, Łostowice",
                    "https://apollo-ireland.akamaized.net/v1/files/eyJmbiI6InNlZ2RlODZsbWkzLUFQTCIsInciOlt7ImZuIjoiajFqM28xM202YmduMS1BUEwiLCJzIjoiMTQiLCJwIjoiMTAsLTEwIiwiYSI6IjAifV19.Ij2KYRSPm7GhzFiuQYWsto9lhtWUae2aP32Rk5obJNs/image;s=1280x1024;q=80",
                    257279.48,
                    43.98,
                    3,
                    "Gdańsk Łostowice, Świętokrzyska Park",
                    OfferType.SELL,
                    PropertyType.FLAT,
                    Vendor.OTODOM
            ),
            getResourceUrl("offer_page4.html") to Offer(
                    getResourceUrl("offer_page4.html"),
                    "Warszawa, Żoliborz",
                    "https://apollo-ireland.akamaized.net/v1/files/eyJmbiI6ImpyejFtdzRhMnl3LUFQTCIsInciOlt7ImZuIjoiajFqM28xM202YmduMS1BUEwiLCJzIjoiMTQiLCJwIjoiMTAsLTEwIiwiYSI6IjAifV19.wZ9EfHzrq0Yw5YWmp3zBXVI3DmiecobgRo2jNt9AfMA/image;s=1280x1024;q=80",
                    25000.0,
                    300.0,
                    4,
                    "Unikalny Dom, Żoliborz Oficerski, Działka 800M2",
                    OfferType.SELL,
                    PropertyType.HOUSE,
                    Vendor.OTODOM
            ),
            getResourceUrl("offer_page5.html") to Offer(
                    getResourceUrl("offer_page5.html"),
                    "Wrocław, Widawa",
                    "https://apollo-ireland.akamaized.net/v1/files/eyJmbiI6InRqeGpwZGV0bXZ3ajEtQVBMIiwidyI6W3siZm4iOiJqMWozbzEzbTZiZ24xLUFQTCIsInMiOiIxNCIsInAiOiIxMCwtMTAiLCJhIjoiMCJ9XX0.mtELF3WDMm6Ykj9I7Uwdm80oMuzI9HfWiB3gFousdio/image;s=1280x1024;q=80",
                    2900.0,
                    84.0,
                    4,
                    "Piętro w domu jednorodzinnym- ogród, parking",
                    OfferType.RENT,
                    PropertyType.HOUSE,
                    Vendor.OTODOM
            ),
            getResourceUrl("offer_page6.html") to Offer(
                    getResourceUrl("offer_page6.html"),
                    "Wrocław, dolnośląskie",
                    "https://apollo-ireland.akamaized.net/v1/files/eyJmbiI6IjZhdWN1MXhsMGRlbDMtQVBMIiwidyI6W3siZm4iOiJqMWozbzEzbTZiZ24xLUFQTCIsInMiOiIxNCIsInAiOiIxMCwtMTAiLCJhIjoiMCJ9XX0.iA-saCsz06E6kVe3zp-t-YSU2Fieagd-msxloTo2o1g/image;s=1280x1024;q=80",
                    9500.0,
                    310.0,
                    8,
                    "Dom na firmę It, biura,",
                    OfferType.RENT,
                    PropertyType.HOUSE,
                    Vendor.OTODOM
            )
    )

}