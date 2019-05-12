package pl.edu.agh.rea.crawler.domain.scraper.extractor

import org.apache.commons.text.StringEscapeUtils
import org.w3c.dom.Document
import pl.edu.agh.rea.crawler.domain.extensions.getSingleStringValue

class StringValueExtractor(private val xPath: String) : Extractor {

    override fun extract(document: Document): String? {
        val stringValue: String = StringEscapeUtils.unescapeHtml4(document.getSingleStringValue(xPath))
                .trim()
                .replace(Regex("\\s+"), " ")
        return if (stringValue.isEmpty()) null else stringValue
    }

}