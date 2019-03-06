package pl.edu.agh.rea.crawler.domain.extensions

import org.assertj.core.api.BDDAssertions.then
import org.htmlcleaner.HtmlCleaner
import org.junit.Test
import org.w3c.dom.Document

class DocumentExtensionsTest {

    @Test
    fun shouldReturnListOfStringValuesWhenGivenXPathExists() {
        //given
        val extensionsPage = getExtensionsTestPage()

        //when
        val multipleStringValues = extensionsPage.getMultipleStringValue("//body/div/p/text()")

        //then
        then(multipleStringValues)
                .isNotEmpty
                .hasSize(2)
                .containsExactly("Some text", "10")
    }

    @Test
    fun shouldReturnEmptyListOfStringValuesWhenGivenXPathDoesNotExist() {
        //given
        val extensionsPage = getExtensionsTestPage()

        //when
        val multipleStringValues = extensionsPage.getMultipleStringValue("/html/body/div/not_exist")

        //then
        then(multipleStringValues).isEmpty()
    }

    @Test
    fun shouldReturnSingleStringValueWhenGivenXPathExists() {
        //given
        val extensionsPage = getExtensionsTestPage()

        //when
        val stringValue = extensionsPage.getSingleStringValue("//p[@id = 'text']/text()")

        //then
        then(stringValue)
                .isNotNull()
                .isEqualTo("Some text")
    }

    @Test
    fun shouldReturnEmptyStringWhenGivenXPathDoesNotExist() {
        //given
        val extensionsPage = getExtensionsTestPage()

        //when
        val stringValue = extensionsPage.getSingleStringValue("//p[@id = 'not_exist']/text()")

        //then
        then(stringValue)
                .isNotNull()
                .isEmpty()
    }

    @Test
    fun shouldReturnSingleIntValueWhenGivenXPathExists() {
        //given
        val extensionsPage = getExtensionsTestPage()

        //when
        val intValue = extensionsPage.getSingleIntValue("//p[@id = 'number']/text()")

        //then
        then(intValue)
                .isNotNull()
                .isEqualTo(10)
    }

    @Test
    fun shouldReturnNullIntValueWhenGivenXPathDoesNotExist() {
        //given
        val extensionsPage = getExtensionsTestPage()

        //when
        val intValue = extensionsPage.getSingleIntValue("//p[@id = 'not_exist']/text()")

        //then
        then(intValue).isNull()
    }

    @Test
    fun shouldReturnNullIntValueWhenValueForGivenXPathIsNotNumber() {
        //given
        val extensionsPage = getExtensionsTestPage()

        //when
        val intValue = extensionsPage.getSingleIntValue("//p[@id = 'text']/text()")

        //then
        then(intValue).isNull()
    }

    private fun getExtensionsTestPage(): Document {
        val extensionsPageUrl = this.javaClass.getResource("/html_cleaner/extensions.html").toString()
        return HtmlCleaner().cleanToDocument(extensionsPageUrl)
    }

}