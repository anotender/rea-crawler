package pl.edu.agh.rea.crawler.domain.scraper

import pl.edu.agh.rea.crawler.domain.model.OfferType
import pl.edu.agh.rea.crawler.domain.model.PropertyType

data class UrlToScrap(val url: String,
                      val offerType: OfferType,
                      val propertyType: PropertyType)