package pl.edu.agh.rea.crawler.domain.htmlcleaner.extensions

import org.htmlcleaner.HtmlCleaner
import org.htmlcleaner.TagNode
import java.net.URL

fun HtmlCleaner.cleanStringUrl(url: String): TagNode {
    return clean(URL(url))
}