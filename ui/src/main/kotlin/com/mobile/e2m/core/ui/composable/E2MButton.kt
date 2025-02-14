package com.mobile.e2m.core.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.core.ui.composable.E2MButtonStyle.*
import com.mobile.e2m.core.ui.theme.alias.E2MAliasColor

enum class E2MButtonStyle { Primary, Gradient, PrimaryFocus, GradientFocus }

@Immutable
data class ButtonColor(
    val background: List<Color>,
    val border: Color,
    val text: Color,
)

private fun getButtonColor(style: E2MButtonStyle, color: E2MAliasColor): ButtonColor {
    return when (style) {
        Primary -> ButtonColor(
            background = listOf(color.surface.textField),
            border = color.border.blur2Light,
            text = color.text.black,
        )

        Gradient -> ButtonColor(
            background = color.gradientPrimaryDefault,
            border = Color.Transparent,
            text = color.text.white,
        )

        PrimaryFocus -> ButtonColor(
            background = listOf(color.surface.whiteButtonFocus),
            border = color.border.blur2Light,
            text = color.text.black,
        )

        GradientFocus -> ButtonColor(
            background = listOf(color.surface.blurButtonFocus),
            border = color.border.blur2Light,
            text = color.text.white,
        )
    }
}

@Composable
fun E2MButton(
    modifier: Modifier = Modifier,
    style: E2MButtonStyle = Primary,
    title: String? = null,
    iconId: Int? = null,
    titleStyle: TextStyle = E2MTheme.typography.base.semiBold,
    onClick: () -> Unit = { },
) {
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color
    val interactionSource = remember { MutableInteractionSource() }
    val buttonColor = remember(style) { getButtonColor(style = style, color = color) }
    val isFocused = interactionSource.collectIsPressedAsState().value
    val currentColor = if (isFocused) {
        getButtonColor(
            style = when (style) {
                Primary -> PrimaryFocus
                Gradient -> GradientFocus
                else -> style
            },
            color = color
        )
    } else {
        buttonColor
    }

    Row(
        modifier = modifier
            .shadowCustom(
                color = color.surface.shadowDark,
                shapeRadius = size.radius.radius7,
                blurRadius = size.spacing.small4x
            )
            .background(
                brush = if (currentColor.background.size > 1) {
                    Brush.verticalGradient(currentColor.background)
                } else {
                    SolidColor(currentColor.background[0])
                },
                shape = RoundedCornerShape(size.radius.radius7),
            )
            .border(
                width = size.stroke.thick,
                color = currentColor.border,
                shape = RoundedCornerShape(size.radius.radius7),
            )
            .focusable(interactionSource = interactionSource)
            .debounceClickable(
                interactionSource = interactionSource
            ) { onClick() },
        horizontalArrangement = Arrangement.spacedBy(size.spacing.smallX, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        iconId?.let {
            E2MAsyncImage(
                modifier = Modifier.size(size.icon.smallX),
                imageId = iconId,
            )
        }

        title?.let {
            Text(
                modifier = Modifier.padding(vertical = size.spacing.small),
                text = it,
                style = titleStyle,
                color = currentColor.text,
            )
        }
    }
}

@Preview(name = "E2MButtonPreview")
@Composable
fun E2MButtonPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        E2MButton(
            modifier = Modifier.fillMaxWidth(),
            title = "Button",
            onClick = {}
        )

        E2MButton(
            modifier = Modifier.fillMaxWidth(),
            title = "Button",
            style = Gradient,
            onClick = {}
        )
    }
}
