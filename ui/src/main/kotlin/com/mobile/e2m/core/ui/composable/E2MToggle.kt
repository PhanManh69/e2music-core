package com.mobile.e2m.core.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobile.e2m.core.ui.theme.E2MTheme

@Immutable
data class E2MToggleableResource(val checkedTrackColor: Color, val uncheckedTrackColor: Color)

enum class E2MToggleType {
    Enabled, Disable
}

@Composable
private fun toggleResourceFactory(type: E2MToggleType): E2MToggleableResource {
    val color = E2MTheme.alias.color.surface

    return when (type) {
        E2MToggleType.Enabled -> E2MToggleableResource(
            checkedTrackColor = color.blur2Light,
            uncheckedTrackColor = color.shadowDark,
        )

        E2MToggleType.Disable -> E2MToggleableResource(
            checkedTrackColor = color.blueLight200,
            uncheckedTrackColor = color.whiteDark
        )
    }
}

@Composable
fun E2MToggle(
    modifier: Modifier = Modifier,
    initChecked: Boolean = false,
    onChecked: ((Boolean) -> Unit)? = null,
    type: E2MToggleType = E2MToggleType.Enabled,
) {
    var checked by remember {
        mutableStateOf(false)
    }

    val token = E2MTheme.alias.color.surface
    val toggleResource = toggleResourceFactory(type)

    Switch(
        modifier = modifier,
        checked = initChecked,
        onCheckedChange = {
            checked = it
            if (type == E2MToggleType.Enabled) {
                onChecked?.invoke(checked)
            }
        },
        thumbContent = {
            Box(
                modifier = Modifier
                    .size(18.dp)
                    .background(
                        color = token.white,
                        shape = CircleShape
                    )
            )
        },
        colors = SwitchDefaults.colors(
            checkedTrackColor = toggleResource.checkedTrackColor,
            uncheckedTrackColor = toggleResource.uncheckedTrackColor,
            checkedThumbColor = token.white,
            uncheckedThumbColor = token.white,
            checkedBorderColor = Color.Transparent,
            uncheckedBorderColor = Color.Transparent,
        )
    )
}

@Preview (name = "Composable Toggle")
@Composable
fun PreviewToggle() {
    var checked by remember { mutableStateOf(false) }
    val checked1 = true
    val checked2 = false

    Column {
        E2MToggle(
            initChecked = checked,
            onChecked = { checked = it }
        )

        E2MToggle(
            initChecked = checked1,
            type = E2MToggleType.Disable
        )

        E2MToggle(
            initChecked = checked2,
            type = E2MToggleType.Disable
        )
    }
}
