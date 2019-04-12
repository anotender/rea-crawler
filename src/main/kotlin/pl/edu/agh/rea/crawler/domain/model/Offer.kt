package pl.edu.agh.rea.crawler.domain.model

data class Offer(
        val offerUrl: String,
        val address: String?,
        val imageUrl: String?,
        val price: Double?,
        val area: Double?,
        val numberOfRooms: Int?,
        val title: String?,
        val offerType: OfferType,
        val propertyType: PropertyType,
        val vendor: Vendor)