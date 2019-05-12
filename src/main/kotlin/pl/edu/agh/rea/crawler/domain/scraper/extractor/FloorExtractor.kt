package pl.edu.agh.rea.crawler.domain.scraper.extractor

import org.w3c.dom.Document
import pl.edu.agh.rea.crawler.domain.extensions.removeNonNumericCharacters

class FloorExtractor(private val stringValueExtractor: Extractor) : Extractor {

    override fun extract(document: Document): Int? {
        return (stringValueExtractor.extract(document) as String?)
                ?.substringBefore('/')
                ?.let {
                    if (it.contains("parter", true)) {
                        return@let "0"
                    } else {
                        return@let it.removeNonNumericCharacters()
                    }
                }
                ?.toInt()
    }

}