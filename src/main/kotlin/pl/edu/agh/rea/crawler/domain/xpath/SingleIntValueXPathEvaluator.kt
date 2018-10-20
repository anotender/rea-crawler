package pl.edu.agh.rea.crawler.domain.xpath

import org.htmlcleaner.TagNode

class SingleIntValueXPathEvaluator(private val singleStringValueXPathEvaluator: XPathEvaluator<String> = SingleStringValueXPathEvaluator()) : XPathEvaluator<Int> {

    override fun evaluate(tagNode: TagNode, xPath: String): Int {
        return singleStringValueXPathEvaluator.evaluate(tagNode, xPath).toInt()
    }

}
