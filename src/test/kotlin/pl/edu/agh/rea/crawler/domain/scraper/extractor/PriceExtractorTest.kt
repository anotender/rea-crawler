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
class PriceExtractorTest(private val stringValue: String, private val expectedResult: Double?) {

    companion object {
        private const val ANY_XPATH: String = "Any XPath"

        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
                arrayOf("", null),
                arrayOf(" ", null),
                arrayOf("1000 zł", 1000.0),
                arrayOf("1 000 zł", 1000.0),
                arrayOf("1000", 1000.0),
                arrayOf("1 000", 1000.0),
                arrayOf("1000,50", 1000.50),
                arrayOf("1000,50 zł", 1000.50),
                arrayOf("1000.50", 1000.50),
                arrayOf("1000.50 zł", 1000.50),
                arrayOf("0", 0.0),
                arrayOf("0.0", 0.0),
                arrayOf("389 950 EUR (1 631 473 zł)", 1631473.0)
        )
    }

    private val priceExtractor = PriceExtractor(StringValueExtractor(ANY_XPATH))

    private val mockDocument: Document = mockk()

    @Before
    fun setUp() {
        mockkStatic("pl.edu.agh.rea.crawler.domain.extensions.DocumentExtensionsKt")
        every { mockDocument.getSingleStringValue(ANY_XPATH) } returns stringValue
    }

    @Test
    fun shouldReturnExpectedResultForGivenDocument() {
        //when
        val result = priceExtractor.extract(mockDocument)

        //then
        then(result).isEqualTo(expectedResult)
    }
}