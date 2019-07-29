package com.yl.kot

import android.util.Log
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.yl.kot.data.entity.User
import com.yl.kot.data.remote.DataManager
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private val context = InstrumentationRegistry.getTargetContext()

    @Test
    fun useAppContext() {
        assertEquals("com.yl.kot", context.packageName)
    }

    @Test
    fun testLogin() {
        DataManager.login("test666", "test666")
            .subscribe(object : TestObserver<User>() {
                override fun onNext(t: User) {
                    Log.e(TAG, "user -> $t")
                    assertEquals(4274, t.id)
                    assertEquals("test666", t.username)
                    assertEquals("", t.password)
                    assertEquals("test666", t.nickname)
                    assertEquals("", t.email)
                    assertEquals("", t.icon)
                    assertEquals(0, t.type)
                    assertFalse(t.admin)
                    assertEquals("", t.token)
                    assertNull(t.chapterTops)
                    assertNull(t.collectIds)
                }
            })
    }

    companion object {
        private const val TAG: String = "ExampleInstrumentedTest"
    }
}
