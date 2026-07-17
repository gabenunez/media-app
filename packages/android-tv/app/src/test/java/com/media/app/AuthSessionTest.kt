package com.media.app

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AuthSessionTest {
    @Test
    fun cookieLookupUrls_includesPathAndOriginVariants() {
        val urls = AuthSession.cookieLookupUrls("https://dotpeenge.crios.bysh.me/reel")
        assertTrue(urls.contains("https://dotpeenge.crios.bysh.me/reel"))
        assertTrue(urls.contains("https://dotpeenge.crios.bysh.me/reel/"))
        assertTrue(urls.contains("https://dotpeenge.crios.bysh.me"))
        assertTrue(urls.contains("https://dotpeenge.crios.bysh.me/"))
        assertEquals(4, urls.size)
    }
}
