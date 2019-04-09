package pl.edu.agh.rea.crawler.domain.store

import org.assertj.core.api.BDDAssertions.then
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mapdb.DB
import org.mapdb.DBMaker
import org.mapdb.HTreeMap
import java.util.*

class VisitedSitesStoreImplTest {

    private lateinit var store: VisitedSitesStore

    private lateinit var db: DB

    @Before
    fun setUp() {
        db = DBMaker.fileDB("test.db").fileDeleteAfterClose().make()
        store = VisitedSitesStoreImpl(db)
    }

    @After
    fun tearDown() {
        if (!db.isClosed()) {
            db.close()
        }
    }

    @Test
    fun shouldPutValueUnderGivenKeyIfThereIsNoSuchKeyYet() {
        //given
        val key = "KEY"
        val value = Date()

        //when
        store.put(key, value)

        //then
        then(store.contains(key))
    }

    @Test
    fun shouldNotPutValueUnderGivenKeyWhenThereIsSuchKeyAlready() {
        //given
        val key = "KEY"
        val firstDate = Date(1000L)
        store.put(key, firstDate)
        val secondDate = Date(2000L)

        //when
        store.put(key, secondDate)

        //then
        then(store.contains(key)).isTrue()
        then(db.get<HTreeMap<String, Date>>("visitedSites")[key])
                .isEqualTo(firstDate)
                .isNotEqualTo(secondDate)
    }

    @Test
    fun shouldNotContainValueWhenGivenWasNotPutInTheStoreBefore() {
        //given
        val key = "DOES_NOT_EXIST"

        //when
        val contains = store.contains(key)

        //then
        then(contains).isFalse()
    }
}