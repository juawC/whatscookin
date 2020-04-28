package com.app.juawcevada.whatscookin.com.app.juawcevada.whatscookin.util

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.navigation.*
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock

fun createNavControllerMock(currentDestinationId: Int = 1): NavController = mock {
    on { currentDestination } doReturn NavDestination("").apply { id = currentDestinationId }
    on { graph } doReturn NavGraph(MockNavigator()).apply {
        startDestination = 1
        addDestination(NavDestination("").apply { id = 1 })
    }
}

fun <T : Fragment> createFactoryWithNavController(
    mockNavController: NavController,
    newFragment: () -> T
): FragmentFactory {
    return object : FragmentFactory() {
        override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
            return newFragment().also { fragment ->
                // In addition to returning a new instance of our Fragment,
                // get a callback whenever the fragment’s view is created
                // or destroyed so that we can set the mock NavController
                fragment.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                    if (viewLifecycleOwner != null) {
                        // The fragment’s view has just been created
                        Navigation.setViewNavController(fragment.requireView(), mockNavController)
                    }
                }
            }
        }
    }
}

@Navigator.Name("Name")
class MockNavigator : Navigator<NavGraph>() {
    override fun navigate(
        destination: NavGraph,
        args: Bundle?,
        navOptions: NavOptions?,
        navigatorExtras: Extras?
    ): NavDestination? {
        return mock()
    }

    override fun createDestination(): NavGraph {
        return mock()
    }

    override fun popBackStack(): Boolean {
        return false
    }
}
