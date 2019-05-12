package pl.edu.agh.rea.crawler.domain.scraper.extractor

import org.w3c.dom.Document
import pl.edu.agh.rea.crawler.domain.extensions.removeNonNumericCharacters

class PriceExtractor(private val stringValueExtractor: Extractor) : Extractor {

    override fun extract(document: Document): Double? {
        val priceStringValue = stringValueExtractor.extract(document) as String?
        val euroIndex = priceStringValue?.indexOf("EUR")
        if (euroIndex != null && euroIndex > 0) {
            return priceStringValue
                    .indexOf("EUR")
                    .let {
                        priceStringValue
                                .substring(it + "EUR".length)
                                .removeNonNumericCharacters()
                                .toDouble()
                    }
        }
        return priceStringValue
                ?.removeNonNumericCharacters()
                ?.toDouble()
    }

}