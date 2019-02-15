package pl.edu.agh.rea.crawler.domain.htmlcleaner.extensions

import org.htmlcleaner.TagNode

fun TagNode.getMultipleStringValue(xPath: String): List<String> {
    return evaluateXPath(xPath).map(Any::toString)
}

fun TagNode.getSingleStringValue(xPath: String): String? {
    return getMultipleStringValue(xPath).firstOrNull()
}

fun TagNode.getSingleIntValue(xPath: String): Int? {
    return getSingleStringValue(xPath)?.toIntOrNull()
}