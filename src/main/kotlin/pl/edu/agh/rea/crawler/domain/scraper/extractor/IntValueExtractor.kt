package pl.edu.agh.rea.crawler.domain.scraper.extractor

import org.w3c.dom.Document
import pl.edu.agh.rea.crawler.domain.extensions.removeNonNumericCharacters

class IntValueExtractor(private val stringValueExtractor: Extractor<String>) : Extractor<Int> {

    override fun extract(document: Document): Int? {
        return stringValueExtractor.extract(document)
                ?.removeNonNumericCharacters()
                ?.toInt()
    }

}
