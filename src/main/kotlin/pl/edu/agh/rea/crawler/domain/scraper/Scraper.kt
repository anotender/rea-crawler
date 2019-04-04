package pl.edu.agh.rea.crawler.domain.scraper

interface Scraper<out T> {

    suspend fun scrap(urlToScrap: UrlToScrap): T

}