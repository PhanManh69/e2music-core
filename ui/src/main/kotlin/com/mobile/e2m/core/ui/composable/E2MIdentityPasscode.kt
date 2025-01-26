package com.mobile.e2m.core.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mobile.e2m.core.ui.theme.E2MTheme

@Composable
fun E2MIdentityPasscode(
    modifier: Modifier = Modifier,
    passcode: String,
    length: Int,
    caption: String? = null,
) {
    val emptyDigit = '_'
    val normalizedPasscode = passcode.take(length).padEnd(length, emptyDigit)
    val style = E2MTheme.typography
    val color = E2MTheme.alias.color
    val size = E2MTheme.alias.size

    Column {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            repeat(length) { index ->
                PasscodeDigit(
                    digit = if (normalizedPasscode[index] == '_') null else normalizedPasscode[index],
                    isFocused = passcode.length == index
                )

                if (index != (length - 1)) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }

        caption?.let {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = size.spacing.smallX),
                text = caption,
                style = style.title.regular,
                color = color.text.error,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
private fun PasscodeDigit(
    digit: Char?,
    isFocused: Boolean = false,
) {
    val style = E2MTheme.typography
    val color = E2MTheme.alias.color
    val size = E2MTheme.alias.size

    Box(
        modifier = Modifier
            .size(size.icon.large)
            .clip(CircleShape)
            .background(if (isFocused) color.surface.textField else color.surface.white)
            .border(
                width = size.stroke.thickX,
                color = if (isFocused) color.border.blurDark else color.border.textField,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = digit?.toString() ?: "_",
            style = style.h4.regular,
            color = if (isFocused) color.text.blurDark else color.text.black
        )
    }
}

@Composable
@Preview
fun E2MIdentityPasscodeFieldPreview() {
    E2MIdentityPasscode(
        passcode = "1223",
        length = 6,
    )
}
