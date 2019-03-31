package pl.edu.agh.rea.crawler.domain.scraper

interface Scraper<T> {

    suspend fun scrap(url: String): T

}