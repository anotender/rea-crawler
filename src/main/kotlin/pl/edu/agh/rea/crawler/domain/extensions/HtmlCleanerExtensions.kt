package pl.edu.agh.rea.crawler.domain.extensions

import org.htmlcleaner.CleanerProperties
import org.htmlcleaner.DomSerializer
import org.htmlcleaner.HtmlCleaner
import org.w3c.dom.Document
import java.net.URL

fun HtmlCleaner.cleanToDocument(url: String): Document {
    val tagNode = clean(URL(url))
    val cleanerProperties = CleanerProperties()
    cleanerProperties.charset = "UTF-8"
    return DomSerializer(cleanerProperties).createDOM(tagNode)
}