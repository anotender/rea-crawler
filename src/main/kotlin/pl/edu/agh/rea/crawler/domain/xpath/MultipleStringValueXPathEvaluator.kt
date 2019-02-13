package pl.edu.agh.rea.crawler.domain.xpath

import org.htmlcleaner.TagNode

class MultipleStringValueXPathEvaluator : XPathEvaluator<List<String>> {

    override fun evaluate(tagNode: TagNode, xPath: String): List<String> {
        return tagNode
                .evaluateXPath(xPath)
                .map { it.toString() }
    }

}