package com.srihi.jcdecaux.velostations

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.srihi.jcdecaux.velostations.ui.MainActivity
import com.srihi.jcdecaux.velostations.ui.stations.list.StationViewHolder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PerformClickViewTest {
    @Rule
    @JvmField var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)
    @Test
    fun testItemClick(){


        Espresso.onView(withId(R.id.stations_list_view))
            .perform(
                RecyclerViewActions
                    .actionOnItemAtPosition<StationViewHolder>(1, ViewActions.click()))
    }
}