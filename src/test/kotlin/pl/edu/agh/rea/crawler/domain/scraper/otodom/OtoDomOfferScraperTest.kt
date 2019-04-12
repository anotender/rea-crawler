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
            )
    )

}