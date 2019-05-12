package pl.edu.agh.rea.crawler.domain.scraper.extractor

import org.w3c.dom.Document
import pl.edu.agh.rea.crawler.domain.extensions.removeNonNumericCharacters

class DoubleValueExtractor(private val stringValueExtractor: Extractor) : Extractor {

    override fun extract(document: Document): Double? {
        return (stringValueExtractor.extract(document) as String?)
                ?.removeNonNumericCharacters()
                ?.toDouble()
    }

}
