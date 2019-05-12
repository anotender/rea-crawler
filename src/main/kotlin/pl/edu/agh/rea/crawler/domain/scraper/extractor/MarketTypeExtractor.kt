package pl.edu.agh.rea.crawler.domain.scraper.extractor

import org.w3c.dom.Document
import pl.edu.agh.rea.crawler.domain.model.MarketType

class MarketTypeExtractor(private val stringValueExtractor: Extractor) : Extractor {

    override fun extract(document: Document): MarketType? {
        return (stringValueExtractor.extract(document) as String?)
                ?.let {
                    return@let when {
                        it.equals("pierwotny", true) -> MarketType.PRIMARY
                        it.equals("wtórny", true) -> MarketType.SECONDARY
                        else -> null
                    }
                }
    }

}