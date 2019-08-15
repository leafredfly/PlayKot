package com.yl.kot

import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import org.junit.Assert.assertEquals
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

    }

    companion object {
        private const val TAG: String = "ExampleInstrumentedTest"
    }
}
