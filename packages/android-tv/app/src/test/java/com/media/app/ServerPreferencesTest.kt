package com.media.app

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ServerPreferencesTest {
    @Test
    fun normalize_keepsHttpsUrlWithoutPortAndWithPath() {
        assertEquals(
            "https://dotpeenge.crios.bysh.me/reel",
            ServerPreferences.normalizeServerUrl(
                "https://dotpeenge.crios.bysh.me/reel",
                "8096",
            ),
        )
    }

    @Test
    fun normalize_keepsHttpsUrlWhenPortFieldEmpty() {
        assertEquals(
            "https://dotpeenge.crios.bysh.me/reel",
            ServerPreferences.normalizeServerUrl(
                "https://dotpeenge.crios.bysh.me/reel",
                "",
            ),
        )
    }

    @Test
    fun normalize_omitsDefaultHttpsPort() {
        assertEquals(
            "https://dotpeenge.crios.bysh.me/reel",
            ServerPreferences.normalizeServerUrl(
                "https://dotpeenge.crios.bysh.me:443/reel",
                "",
            ),
        )
    }

    @Test
    fun normalize_publicHostWithPathDefaultsToHttpsWithoutPort() {
        assertEquals(
            "https://dotpeenge.crios.bysh.me/reel",
            ServerPreferences.normalizeServerUrl(
                "dotpeenge.crios.bysh.me/reel",
                "",
            ),
        )
    }

    @Test
    fun normalize_doesNotAppendPortAfterPath() {
        assertEquals(
            "http://dotpeenge.crios.bysh.me:8096/reel",
            ServerPreferences.normalizeServerUrl(
                "dotpeenge.crios.bysh.me/reel",
                "8096",
            ),
        )
    }

    @Test
    fun normalize_lanHostDefaultsToMediaPort() {
        assertEquals(
            "http://192.168.1.10:8096",
            ServerPreferences.normalizeServerUrl("192.168.1.10", ""),
        )
    }

    @Test
    fun normalize_lanHostUsesExplicitPort() {
        assertEquals(
            "http://10.0.0.5:9000",
            ServerPreferences.normalizeServerUrl("10.0.0.5", "9000"),
        )
    }

    @Test
    fun normalize_rejectsEmptyHost() {
        assertNull(ServerPreferences.normalizeServerUrl("   ", "8096"))
    }

    @Test
    fun isLanOrLocalHost_detectsPrivateRanges() {
        assertTrue(ServerPreferences.isLanOrLocalHost("192.168.1.10"))
        assertTrue(ServerPreferences.isLanOrLocalHost("10.0.0.1"))
        assertTrue(ServerPreferences.isLanOrLocalHost("172.16.4.2"))
        assertTrue(ServerPreferences.isLanOrLocalHost("localhost"))
        assertTrue(ServerPreferences.isLanOrLocalHost("media.local"))
        assertFalse(ServerPreferences.isLanOrLocalHost("dotpeenge.crios.bysh.me"))
        assertFalse(ServerPreferences.isLanOrLocalHost("example.com"))
    }
}
