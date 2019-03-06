package pl.edu.agh.rea.crawler.domain.extensions

import org.w3c.dom.Document
import org.w3c.dom.NodeList
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory

private val xPathFactory: XPathFactory = XPathFactory.newInstance()

fun Document.getMultipleStringValue(xPath: String): List<String> {
    val nodeList: NodeList = xPathFactory.newXPath().evaluate(xPath, this, XPathConstants.NODESET) as NodeList
    val stringValues: MutableList<String> = mutableListOf()

    for (i in 0 until nodeList.length) {
        val textContent = nodeList.item(i).textContent
        stringValues.add(textContent)
    }

    return stringValues
}

fun Document.getSingleStringValue(xPath: String): String? {
    return xPathFactory.newXPath().evaluate(xPath, this, XPathConstants.STRING) as String
}

fun Document.getSingleIntValue(xPath: String): Int? {
    return getSingleStringValue(xPath)?.toIntOrNull()
}
