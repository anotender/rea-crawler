package pl.edu.agh.rea.crawler.domain.scraper.extractor

import org.w3c.dom.Document

interface Extractor {

    fun extract(document: Document): Any?

}