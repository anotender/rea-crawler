package pl.edu.agh.rea.crawler.domain.xpath

import org.htmlcleaner.TagNode

class SingleStringValueXPathEvaluator(private val multipleStringValueXPathEvaluator: XPathEvaluator<List<String>> = MultipleStringValueXPathEvaluator()) : XPathEvaluator<String?> {

    override fun evaluate(tagNode: TagNode, xPath: String): String? {
        return multipleStringValueXPathEvaluator.evaluate(tagNode, xPath).firstOrNull()
    }

}
