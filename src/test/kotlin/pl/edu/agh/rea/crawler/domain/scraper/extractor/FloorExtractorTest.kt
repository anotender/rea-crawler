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
class FloorExtractorTest(private val stringValue: String, private val expectedResult: Int?) {

    companion object {
        private const val ANY_XPATH: String = "Any XPath"

        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
                arrayOf("", null),
                arrayOf(" ", null),
                arrayOf("parter", 0),
                arrayOf("0", 0),
                arrayOf("0/10", 0),
                arrayOf("1/10", 1),
                arrayOf("1", 1),
                arrayOf("2/10", 2),
                arrayOf("2", 2),
                arrayOf("5/5", 5)
        )
    }

    private val floorExtractor = FloorExtractor(StringValueExtractor(ANY_XPATH))

    private val mockDocument: Document = mockk()

    @Before
    fun setUp() {
        mockkStatic("pl.edu.agh.rea.crawler.domain.extensions.DocumentExtensionsKt")
        every { mockDocument.getSingleStringValue(ANY_XPATH) } returns stringValue
    }

    @Test
    fun shouldReturnExpectedResultForGivenDocument() {
        //when
        val result = floorExtractor.extract(mockDocument)

        //then
        then(result).isEqualTo(expectedResult)
    }
}