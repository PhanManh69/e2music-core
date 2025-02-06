package com.mobile.e2m.core.ui.composable

import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
inline fun Modifier.debounceClickable(
    debounceInterval: Long = 800,
    interactionSource: MutableInteractionSource? = remember { MutableInteractionSource() },
    indication: Indication? = null,
    crossinline onClick: () -> Unit,
): Modifier {
    var lastClickTime = 0L

    return clickable(
        interactionSource = interactionSource,
        indication = indication
    ) {
        val currentTime = System.currentTimeMillis()
        if ((currentTime - lastClickTime) < debounceInterval) return@clickable
        lastClickTime = currentTime
        onClick()
    }
}
