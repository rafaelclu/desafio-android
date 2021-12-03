package com.picpay.desafio.android.presentation.view.main

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.MediumTest
import androidx.test.platform.app.InstrumentationRegistry
import com.picpay.desafio.android.R
import com.picpay.desafio.android.utils.MockWebServerRule
import com.picpay.desafio.android.utils.RecyclerViewMatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

@MediumTest
class MainActivityTest {

    @get:Rule
    val mockWebServerRule = MockWebServerRule()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun shouldDisplayTitle() {
        launchActivity<MainActivity>().apply {
            val expectedTitle = context.getString(R.string.title)

            moveToState(Lifecycle.State.RESUMED)

            onView(withText(expectedTitle)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun shouldDisplayListItem() {
        runBlocking {
            launchActivity<MainActivity>().apply {

                delay(200L)

                RecyclerViewMatchers.checkRecyclerViewItem(
                    resId = R.id.recyclerView,
                    position = 0,
                    withMatcher = withText("Eduardo Santos")
                )
            }
        }
    }


}