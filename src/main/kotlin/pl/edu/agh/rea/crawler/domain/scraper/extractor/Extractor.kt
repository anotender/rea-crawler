package pl.edu.agh.rea.crawler.domain.scraper.extractor

import org.w3c.dom.Document

interface Extractor<out T> {

    fun extract(document: Document): T?

}