package pl.edu.agh.rea.crawler.domain

import kotlinx.coroutines.experimental.runBlocking
import org.assertj.core.api.BDDAssertions.then
import org.htmlcleaner.HtmlCleaner
import org.junit.Test
import pl.edu.agh.rea.crawler.configuration.properties.VendorConfigurationProperties

class OfferUrlsCrawlerTest {

    @Test
    fun shouldReturnListOfUrlsForGivenPage() {
        //given
        val url = this.javaClass.getResource("/test.html")
        val vendorConfigurationProperties = VendorConfigurationProperties()
        vendorConfigurationProperties.offerUrlXpath = "//*[@id=\"contentPage\"]/div/div/div/div/section/div/div/div/div/section/header/a/@href"
        val offerUrlsCrawler = OfferUrlsCrawler(vendorConfigurationProperties, url, HtmlCleaner())

        //when
        val result = runBlocking { offerUrlsCrawler.fetch() }

        //then
        then(result)
                .isNotNull
                .hasSize(3)
                .containsExactly(
                        "https://www.morizon.pl/oferta/sprzedaz-mieszkanie-krakow-mistrzejowice-73m2-mzn2032058697",
                        "https://www.morizon.pl/oferta/sprzedaz-mieszkanie-krakow-grzegorzki-34m2-mzn2032058695",
                        "https://www.morizon.pl/oferta/sprzedaz-mieszkanie-bytom-szombierki-zabrzanska-65m2-mzn2032057520"
                )

    }

}