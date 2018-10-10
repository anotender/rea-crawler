package pl.edu.agh.rea.crawler.domain.xpath

import org.htmlcleaner.TagNode

interface XPathEvaluator<T> {

    fun evaluate(tagNode: TagNode, xPath: String): T

}