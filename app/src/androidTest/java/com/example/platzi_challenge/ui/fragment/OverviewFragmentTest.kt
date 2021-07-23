package com.example.platzi_challenge.ui.fragment

import android.view.View
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.example.platzi_challenge.R
import com.example.platzi_challenge.data.Beer
import com.example.platzi_challenge.network.api.BeerService
import com.example.platzi_challenge.utils.launchTestFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KTextView
import io.mockk.coEvery
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class OverviewFragmentTest {
    @get:Rule val hiltRule = HiltAndroidRule(this)

    @Inject lateinit var fakeService: BeerService

    private val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

    private val screen = OverviewScreen()

    class OverviewScreen : Screen<OverviewScreen>() {
        val beersRv = KRecyclerView(
            builder = { withId(R.id.beers_rv) },
            itemTypeBuilder = { itemType(::Item) }
        )

        class Item(parent: Matcher<View>) : KRecyclerItem<Item>(parent) {
            val nameTv = KTextView(parent) { withId(R.id.name_tv) }
            val taglineTv = KTextView(parent) { withId(R.id.tagline_tv) }
        }
    }

    @Before fun setup() {
        hiltRule.inject()
    }

    @Test
    fun showsData() = runBlocking {
        // configure
        coEvery { fakeService.getBeers(any(), any()) } returns generateList()

        // act
        navController.launchTestFragment<OverviewFragment>(R.id.overviewFragment)

        // assert
        screen {
            beersRv.hasSize(20)
            beersRv.children<OverviewScreen.Item> {
                nameTv.containsText(testBeer.name)
                taglineTv.containsText(testBeer.tagline)
            }
        }
    }

    private fun generateList(): List<Beer> {
        val result = mutableListOf<Beer>()
        repeat(20) { result.add(testBeer) }
        return  result
    }
}

val testBeer = Beer(
    id = 1,
    name = "Buzz",
    tagline = "A Real Bitter Experience.",
    firstBrewed = "09/2007",
    description = "A light, crisp and bitter IPA brewed with English and American hops. A small batch brewed only once.",
    imageUrl = "https://images.punkapi.com/v2/keg.png",
    abv = 4.5,
    ibu = 60.0,
    ph = 4.4,
    brewersTips = "The earthy and floral aromas from the hops can be overpowering. Drop a little Cascade in at the end of the boil to lift the profile with a bit of citrus.",
)