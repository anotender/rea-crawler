package pl.edu.agh.rea.crawler.domain.store

import java.util.*

interface VisitedSitesStore {

    fun put(k: String, v: Date)

    fun contains(k: String): Boolean

}