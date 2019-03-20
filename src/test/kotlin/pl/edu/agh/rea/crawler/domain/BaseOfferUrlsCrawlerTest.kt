package pl.edu.agh.rea.crawler.domain

import org.htmlcleaner.HtmlCleaner

abstract class BaseOfferUrlsCrawlerTest(vendorName: String) : BaseCrawlerTest(vendorName) {

    protected fun getOfferUrlsCrawler(): OfferUrlsCrawler = OfferUrlsCrawler(getVendorConfiguration(), HtmlCleaner())

}