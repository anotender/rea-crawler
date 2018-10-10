package pl.edu.agh.rea.crawler.domain.xpath

import org.htmlcleaner.TagNode

class XPathEvaluatorFactory {

    val tagNodeListXPathEvaluator: XPathEvaluator<List<TagNode>> = TagNodeListXPathEvaluator()

    val textValueXPathEvaluator: XPathEvaluator<String?> = TextValueXPathEvaluator()

    val attributeValueXPathEvaluator: XPathEvaluator<String?> = AttributeValueXPathEvaluator()

}