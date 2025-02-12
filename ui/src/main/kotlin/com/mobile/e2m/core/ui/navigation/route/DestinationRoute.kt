package com.mobile.e2m.core.ui.navigation.route

import kotlinx.serialization.Serializable

@Serializable
sealed class AppNavigationRoute {

    @Serializable
    data object Account : AppNavigationRoute() {

        @Serializable
        data object Begin : AppNavigationRoute()

        @Serializable
        data object Started : AppNavigationRoute()

        @Serializable
        data object Login : AppNavigationRoute()

        @Serializable
        data object ForgotPassword : AppNavigationRoute()

        @Serializable
        data class ResetPassword(val userId: Int) : AppNavigationRoute() {
            fun toRoute(): String = "reset_password/$userId"

            companion object {
                const val ROUTE = "reset_password/{userId}"
            }
        }

        @Serializable
        data object Register : AppNavigationRoute()

        @Serializable
        data object RegistrationSuccessful : AppNavigationRoute()
    }

    @Serializable
    data object Dashboard : AppNavigationRoute() {

        @Serializable
        data object Home : AppNavigationRoute()

        @Serializable
        data object Music : AppNavigationRoute()

        @Serializable
        data object Profile : AppNavigationRoute()
    }

    @Serializable
    data object Music : AppNavigationRoute() {

        @Serializable
        data object Song : AppNavigationRoute()

        @Serializable
        data object Playlist : AppNavigationRoute()

        @Serializable
        data object Album : AppNavigationRoute()

        @Serializable
        data object Artist : AppNavigationRoute()

        @Serializable
        data object Genre : AppNavigationRoute()
    }

    @Serializable
    data object Main : AppNavigationRoute() {

        @Serializable
        data object Home : AppNavigationRoute()
    }
}
