package com.mobile.e2m.core.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mobile.e2m.core.ui.composable.inputField.E2MTextField
import com.mobile.e2m.core.ui.theme.E2MTheme

@Composable
fun E2MIdentityPasscode(
    modifier: Modifier = Modifier,
    length: Int = 5,
    caption: String? = null,
    doneOnClick: () -> Unit = { },
) {
    var passcode by remember { mutableStateOf("") }

    val emptyDigit = '_'
    val normalizedPasscode = passcode.take(length).padEnd(length, emptyDigit)
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    val style = E2MTheme.typography
    val color = E2MTheme.alias.color
    val size = E2MTheme.alias.size

    Column {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        keyboardController?.show()
                        focusRequester.requestFocus()
                    },
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

            E2MTextField(
                modifier = Modifier
                    .offset(-(size.spacing.max))
                    .focusRequester(focusRequester),
                value = passcode,
                onValueChange = { newValue ->
                    if (newValue.length <= length && newValue.all { it.isDigit() }) {
                        passcode = newValue
                    }
                },
                textStyle = style.base.none,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                        doneOnClick()
                    }
                )
            )
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
        length = 5,
    )
}
