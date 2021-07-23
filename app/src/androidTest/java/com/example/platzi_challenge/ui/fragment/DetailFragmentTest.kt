package com.example.platzi_challenge.ui.fragment

import androidx.core.os.bundleOf
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.example.platzi_challenge.R
import com.example.platzi_challenge.utils.launchTestFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KTextView
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class DetailFragmentTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

    private val screen = DetailFragmentScreen()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun showsBeerData() {
        // configure
        val args = bundleOf("beer" to testBeer)

        // act
        navController.launchTestFragment<DetailFragment>(R.id.detailFragment, args)

        //assert
        screen {
            nameTv.containsText(testBeer.name)
            taglineTv.containsText(testBeer.tagline)
            descriptionTv.containsText(testBeer.description)
            abvTv.containsText("${testBeer.abv}\nAlcohol by volume")
            ibuTv.containsText("${testBeer.ibu}\nInternational bitterness units")
            phTv.containsText("${testBeer.ph}\nPotential of hydrogen")
        }
    }

    class DetailFragmentScreen : Screen<DetailFragmentScreen>() {
        val nameTv = KTextView { withId(R.id.name_tv) }
        val taglineTv = KTextView { withId(R.id.tagline_tv) }
        val descriptionTv = KTextView { withId(R.id.description_tv) }
        val abvTv = KTextView { withId(R.id.abv_tv) }
        val ibuTv = KTextView { withId(R.id.ibu_tv) }
        val phTv = KTextView { withId(R.id.ph_tv) }
    }
}