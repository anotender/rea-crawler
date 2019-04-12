package pl.edu.agh.rea.crawler.domain.crawler

import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.mock
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import pl.edu.agh.rea.crawler.configuration.properties.VendorConfiguration
import pl.edu.agh.rea.crawler.domain.model.Offer
import pl.edu.agh.rea.crawler.domain.scraper.OfferScraper
import pl.edu.agh.rea.crawler.domain.scraper.UrlToScrap
import pl.edu.agh.rea.crawler.domain.sender.OfferSender

@RunWith(MockitoJUnitRunner::class)
class OfferCrawlerTest {

    @Mock
    private lateinit var mockVendorConfiguration: VendorConfiguration

    @Mock
    private lateinit var mockOfferScraper: OfferScraper

    @Mock
    private lateinit var mockOffer: Offer

    @Mock
    private lateinit var mockUrlToScrap: UrlToScrap

    private val offerSenders = listOf(
            mock(OfferSender::class.java),
            mock(OfferSender::class.java),
            mock(OfferSender::class.java)
    )

    @Test
    fun shouldNotRunAnyProcessingWhenThereAreNoUrlsToScrap() {
        //given
        given(mockVendorConfiguration.concurrentRequestsCount).willReturn(2)
        val urlsToScrap = getUrlsToScrap(0)
        val offerCrawler = OfferCrawler(mockVendorConfiguration, urlsToScrap, mockOfferScraper, offerSenders)

        //when
        offerCrawler.run()

        //then
        assertThat(urlsToScrap).isEmpty()
        verifyZeroInteractions(mockOfferScraper)
        offerSenders.forEach { verifyZeroInteractions(it) }
    }

    @Test
    fun shouldScrapAtMostConcurrentRequestCountTimes() = runBlocking {
        //given
        given(mockVendorConfiguration.concurrentRequestsCount).willReturn(2)
        val urlsToScrap = getUrlsToScrap(5)
        urlsToScrap.forEach {
            given(mockOfferScraper.scrap(it)).willReturn(mockOffer)
        }
        val offerCrawler = OfferCrawler(mockVendorConfiguration, urlsToScrap, mockOfferScraper, offerSenders)

        //when
        offerCrawler.run()

        //then
        assertThat(urlsToScrap).hasSize(3)
        verify(mockOfferScraper, times(2)).scrap(mockUrlToScrap)
        offerSenders.forEach { verify(it, times(2)).invoke(mockOffer) }
    }

    @Test
    fun shouldScrapAllUrlsToScrapWhenThereIsLessUrlsThanConcurrentRequestCount() = runBlocking {
        //given
        given(mockVendorConfiguration.concurrentRequestsCount).willReturn(2)
        val urlsToScrap = getUrlsToScrap(1)
        urlsToScrap.forEach {
            given(mockOfferScraper.scrap(it)).willReturn(mockOffer)
        }

        val offerCrawler = OfferCrawler(mockVendorConfiguration, urlsToScrap, mockOfferScraper, offerSenders)

        //when
        offerCrawler.run()

        //then
        assertThat(urlsToScrap).isEmpty()
        verify(mockOfferScraper, times(1)).scrap(mockUrlToScrap)
        offerSenders.forEach { verify(it, times(1)).invoke(mockOffer) }
    }

    private fun getUrlsToScrap(count: Int): MutableList<UrlToScrap> {
        val urlsToScrap = mutableListOf<UrlToScrap>()

        for (i in count downTo 1) {
            urlsToScrap.add(mockUrlToScrap)
        }

        return urlsToScrap
    }

}