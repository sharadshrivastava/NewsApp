package com.test.newsapp

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import kotlinx.android.synthetic.main.main_activity.*
import org.junit.After
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)


    @After
    fun cleanup() {
        rule.scenario.close()
    }

    @Test
    fun testFragmentLoaded() {
        rule.scenario.onActivity {
            val fragment = it.navHostFragment
            Assert.assertNotNull(fragment)
        }
    }
}
