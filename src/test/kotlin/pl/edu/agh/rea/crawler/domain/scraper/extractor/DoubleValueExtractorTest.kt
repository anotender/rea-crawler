package pl.edu.agh.rea.crawler.domain.scraper.extractor

import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import org.assertj.core.api.BDDAssertions.then
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.w3c.dom.Document
import pl.edu.agh.rea.crawler.domain.extensions.getSingleStringValue

@RunWith(Parameterized::class)
class DoubleValueExtractorTest(private val stringValue: String, private val expectedResult: Double?) {

    companion object {
        private const val ANY_XPATH: String = "Any XPath"

        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
                arrayOf("", null),
                arrayOf(" ", null),
                arrayOf("non numeric 43.123 chars", 43.123),
                arrayOf("43.123", 43.123),
                arrayOf("43,123", 43.123),
                arrayOf("43", 43.0),
                arrayOf("0", 0.0),
                arrayOf("0.0", 0.0),
                arrayOf(" 43.123   ", 43.123),
                arrayOf("non&nbsp;numeric&nbsp;43.123&nbsp;chars", 43.123)
        )
    }

    private val doubleValueExtractor = DoubleValueExtractor(StringValueExtractor(ANY_XPATH))

    private val mockDocument: Document = mockk()

    @Before
    fun setUp() {
        mockkStatic("pl.edu.agh.rea.crawler.domain.extensions.DocumentExtensionsKt")
        every { mockDocument.getSingleStringValue(ANY_XPATH) } returns stringValue
    }

    @Test
    fun shouldReturnExpectedResultForGivenDocument() {
        //when
        val result = doubleValueExtractor.extract(mockDocument)

        //then
        then(result).isEqualTo(expectedResult)
    }
}