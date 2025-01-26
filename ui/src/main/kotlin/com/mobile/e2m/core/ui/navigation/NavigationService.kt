package com.mobile.e2m.core.ui.navigation

import androidx.navigation.NavOptionsBuilder
import com.mobile.e2m.core.ui.navigation.utils.DestinationRoute

interface NavigationService {
    fun navigateTo(destination: DestinationRoute, navOptions: NavOptionsBuilder.() -> Unit = {})
    fun goBack()
}
