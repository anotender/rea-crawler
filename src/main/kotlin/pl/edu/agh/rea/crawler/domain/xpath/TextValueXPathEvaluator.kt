package pl.edu.agh.rea.crawler.domain.xpath

import org.htmlcleaner.TagNode

class TextValueXPathEvaluator : XPathEvaluator<String?> {

    override fun evaluate(tagNode: TagNode, xPath: String): String? {
        return tagNode
                .evaluateXPath(xPath)
                .asList()
                .asSequence()
                .map { it as TagNode }
                .map { it.text.toString() }
                .firstOrNull()
    }

}
