package com.pras.submission1jetpack.view

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.pras.submission1jetpack.R
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import androidx.test.core.app.ActivityScenario
import com.pras.submission1jetpack.model.*
import com.pras.submission1jetpack.model.local.entity.MovieListEntity
import com.pras.submission1jetpack.model.local.entity.TvListEntity
import com.pras.submission1jetpack.utils.EspressoIdlingResource

class MainActivityTest {

    private val movieData = ArrayList<MovieListEntity>()
    private val tvShowData = ArrayList<TvListEntity>()
    val movie = DummyData.generateMovieDetail()
    val tvShow = DummyData.generateTvDetail()


    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
//        ActivityScenario.launch(FavoriteActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun loadMovie() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                movieData.size
            )
        )
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(withText(movie.original_title)))
        onView(withId(R.id.tv_overview)).check(matches(withText(movie.overview)))
        onView(withId(R.id.tv_playtime)).check(matches(withText(movie.release_date)))
        onView(withId(R.id.btn_favorite)).perform(click())
        onView(withId(R.id.backButton)).perform(click())

        //Check favorite movie
        onView(withId(R.id.btn_favorite)).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(withText(movie.original_title)))
        onView(withId(R.id.tv_overview)).check(matches(withText(movie.overview)))
        onView(withId(R.id.tv_playtime)).check(matches(withText(movie.release_date)))
    }

    @Test
    fun loadTvShow() {
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                tvShowData.size
            )
        )
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(withText(tvShow.name)))
        onView(withId(R.id.tv_overview)).check(matches(withText(tvShow.overview)))
        onView(withId(R.id.tv_playtime)).check(matches(withText(tvShow.first_air_date)))
        onView(withId(R.id.btn_favorite)).perform(click())
        onView(withId(R.id.backButton)).perform(click())

        onView(withId(R.id.btn_favorite)).perform(click())
        onView(withText("Favorite TV Shows")).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(withText(tvShow.name)))
        onView(withId(R.id.tv_overview)).check(matches(withText(tvShow.overview)))
        onView(withId(R.id.tv_playtime)).check(matches(withText(tvShow.first_air_date)))
    }
}