package pl.edu.agh.rea.crawler.domain.scraper.domiporta

import pl.edu.agh.rea.crawler.domain.scraper.BaseOfferUrlsScraperTest

class DomiportaOfferUrlsScraperTest : BaseOfferUrlsScraperTest("domiporta") {

    override fun getExpectedUrls(): List<String> = listOf(
            "https://www.domiporta.pl/nieruchomosci/wynajme-mieszkanie-trzypokojowe-poznan-jezyce-nalkowskiej-56m2/150350586?clickSource=Premium",
            "https://www.domiporta.pl/nieruchomosci/wynajme-mieszkanie-warszawa-praga-poludnie-ul-obroncow-140m2/145314795?clickSource=Premium",
            "https://www.domiporta.pl/nieruchomosci/wynajme-mieszkanie-dwupokojowe-warszawa-praga-polnoc-ul-jagiellonska-54m2/150333016?clickSource=Premium",
            "https://www.domiporta.pl/nieruchomosci/wynajme-mieszkanie-dwupokojowe-warszawa-bialoleka-ul-sieczna-40m2/150294421?clickSource=Premium",
            "https://www.domiporta.pl/nieruchomosci/wynajme-mieszkanie-dwupokojowe-warszawa-praga-poludnie-ul-zwyciezcow-54m2/150233116?clickSource=Premium"
    )

}