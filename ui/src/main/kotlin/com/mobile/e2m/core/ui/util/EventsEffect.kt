package com.mobile.e2m.core.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.mobile.e2m.core.ui.base.E2MBaseViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * Convenience method for observing event flow from [E2MBaseViewModel].
 */
@Composable
fun <E> EventsEffect(
    viewModel: E2MBaseViewModel<*, E, *>,
    handler: suspend (E) -> Unit,
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.eventFlow
            .onEach { handler.invoke(it) }
            .launchIn(this)
    }
}
