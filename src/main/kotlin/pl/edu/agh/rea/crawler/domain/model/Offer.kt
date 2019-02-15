package pl.edu.agh.rea.crawler.domain.model

data class Offer(
        val offerUrl: String,
        val address: String,
        val imageUrl: String,
        val price: String,
        val area: String,
        val numberOfRooms: Int)