package pl.edu.agh.rea.crawler.domain

interface Crawler<T> {

    suspend fun fetch(): T

}