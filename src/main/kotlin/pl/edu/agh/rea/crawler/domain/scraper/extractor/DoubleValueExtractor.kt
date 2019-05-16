package pl.edu.agh.rea.crawler.domain.scraper.extractor

import org.w3c.dom.Document
import pl.edu.agh.rea.crawler.domain.extensions.removeNonNumericCharacters

class DoubleValueExtractor(private val stringValueExtractor: Extractor<String>) : Extractor<Double> {

    override fun extract(document: Document): Double? {
        return stringValueExtractor.extract(document)
                ?.removeNonNumericCharacters()
                ?.toDouble()
    }

}
