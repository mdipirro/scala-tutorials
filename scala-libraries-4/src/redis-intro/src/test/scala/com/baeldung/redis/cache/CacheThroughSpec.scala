package com.baeldung.redis.cache

import com.baeldung.redis.db.{BooksDB, VirtualDatabase}
import com.baeldung.redis.util.RedisSpec
import org.mockito.Mockito._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatestplus.mockito.MockitoSugar

class CacheThroughSpec extends AnyFlatSpec with RedisSpec with MockitoSugar {

  "CacheThrough" should "fetch from DB only the first time" in {
    val mockDb = mock[VirtualDatabase]
    when(mockDb.books()).thenReturn(BooksDB.books)
    val cacheThrough = new CacheThrough(getJedis(), mockDb)
    cacheThrough.books()
    cacheThrough.books()
    verify(mockDb, times(1)).books()
  }

}
