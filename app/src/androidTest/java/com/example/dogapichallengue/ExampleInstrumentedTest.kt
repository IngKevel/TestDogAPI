package com.example.dogapichallengue

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.dogapichallengue.domain.models.DogModel
import com.example.dogapichallengue.ui.MainActivity
import com.example.dogapichallengue.ui.dogpage.DogPageView
import com.example.dogapichallengue.ui.dogpage.DogPageViewContent
import com.example.dogapichallengue.ui.dogpage.DogPageViewState
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest

import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    var composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun getNewDogIsLoading() {
        composeTestRule.activity.setContent {
            DogPageViewContent(
                dogPageViewState = DogPageViewState.Loading,
                dogModel = DogModel("", "")
            ) {

            }
        }

        composeTestRule.onNodeWithText("Loading").assertExists()
    }
}