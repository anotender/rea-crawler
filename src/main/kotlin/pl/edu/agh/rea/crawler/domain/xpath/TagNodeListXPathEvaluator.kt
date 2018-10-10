package pl.edu.agh.rea.crawler.domain.xpath

import org.htmlcleaner.TagNode

class TagNodeListXPathEvaluator : XPathEvaluator<List<TagNode>> {

    override fun evaluate(tagNode: TagNode, xPath: String): List<TagNode> {
        return tagNode
                .evaluateXPath(xPath)
                .asList()
                .map { it as TagNode }
    }

}