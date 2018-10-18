package pl.edu.agh.rea.crawler.domain.xpath

import org.htmlcleaner.TagNode

class SingleStringValueXPathEvaluator : XPathEvaluator<String> {

    override fun evaluate(tagNode: TagNode, xPath: String): String {
        return tagNode
                .evaluateXPath(xPath)
                .asList()
                .asSequence()
                .map { it.toString() }
                .firstOrNull()
                .orEmpty()
    }

}
