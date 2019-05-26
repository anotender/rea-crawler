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
import pl.edu.agh.rea.crawler.domain.model.MarketType

@RunWith(Parameterized::class)
class MarketTypeExtractorTest(private val stringValue: String, private val expectedResult: MarketType?) {

    companion object {
        private const val ANY_XPATH: String = "Any XPath"

        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
                arrayOf("", null),
                arrayOf(" ", null),
                arrayOf("any different chars", null),
                arrayOf("non&nbsp;numeric&nbsp;43.123&nbsp;chars", null),
                arrayOf("pierwotny", MarketType.PRIMARY),
                arrayOf("   pierwotny ", MarketType.PRIMARY),
                arrayOf("wtórny", MarketType.SECONDARY),
                arrayOf("   wtórny ", MarketType.SECONDARY)
        )
    }

    private val marketTypeExtractor = MarketTypeExtractor(StringValueExtractor(ANY_XPATH))

    private val mockDocument: Document = mockk()

    @Before
    fun setUp() {
        mockkStatic("pl.edu.agh.rea.crawler.domain.extensions.DocumentExtensionsKt")
        every { mockDocument.getSingleStringValue(ANY_XPATH) } returns stringValue
    }

    @Test
    fun shouldReturnExpectedResultForGivenDocument() {
        //when
        val result = marketTypeExtractor.extract(mockDocument)

        //then
        then(result).isEqualTo(expectedResult)
    }
}