package pl.edu.agh.rea.crawler.domain.xpath

import org.htmlcleaner.TagNode

class AttributeValueXPathEvaluator : XPathEvaluator<String?> {

    override fun evaluate(tagNode: TagNode, xPath: String): String? {
        return tagNode.evaluateXPath(xPath).firstOrNull() as String?
    }

}
