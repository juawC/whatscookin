package com.app.juawcevada.whatscookin.util

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.app.juawcevada.whatscookin.com.app.juawcevada.whatscookin.util.RecyclerViewMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

infix fun ViewInteraction.checkThatMatches(matcher: Matcher<in View>): ViewInteraction =
    this.check(ViewAssertions.matches(matcher))

infix fun Int.checkThatMatches(matcher: Matcher<in View>): ViewInteraction =
    onView().checkThatMatches(matcher)

infix fun String.checkThatMatches(matcher: Matcher<in View>): ViewInteraction =
    Espresso.onView(ViewMatchers.withText(this)).checkThatMatches(matcher)

infix fun Int.onRecyclerViewPosition(position: Int): ViewInteraction =
    Espresso.onView(RecyclerViewMatcher(this).atPosition(position))

infix fun ViewInteraction.perform(viewAction: ViewAction) {
    this.perform(viewAction)
}

infix fun Int.perform(viewAction: ViewAction) {
    onView().perform(viewAction)
}

fun Int.onView(): ViewInteraction = Espresso.onView(ViewMatchers.withId(this))

fun all(body: AllOfBuilder.() -> Unit): Matcher<in View> = AllOfBuilder().apply(body).build()

class AllOfBuilder {
    private val matchers = mutableListOf<Matcher<in View>>()

    fun matcher(matcher: () -> Matcher<in View>) {
        matchers.add(matcher())
    }

    fun build() = allOf(matchers)
}