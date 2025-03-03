package com.mobile.e2m.core.ui.composable.inputField

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mobile.e2m.core.ui.composable.debounceClickable
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.core.ui.util.ValueConfig.MAX_LENGTH_PASSCODE

@Composable
fun E2MIdentityPasscode(
    modifier: Modifier = Modifier,
    initPasscode: String = "",
    caption: String? = null,
    length: Int = MAX_LENGTH_PASSCODE,
    onPasscodeChange: (String) -> Unit = { },
    doneOnClick: () -> Unit = { },
) {
    var passcode by remember { mutableStateOf(initPasscode) }
    var isFocusedTextField by remember { mutableStateOf(false) }

    val emptyDigit = '_'
    val normalizedPasscode = passcode.take(length).padEnd(length, emptyDigit)
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    val style = E2MTheme.typography
    val color = E2MTheme.alias.color
    val size = E2MTheme.alias.size

    LaunchedEffect(initPasscode) {
        passcode = initPasscode
    }

    Column {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .debounceClickable {
                        keyboardController?.show()
                        focusRequester.requestFocus()
                    },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                repeat(length) { index ->
                    PasscodeDigit(
                        digit = if (normalizedPasscode[index] == '_') null else normalizedPasscode[index],
                        isFocused = passcode.length == index,
                        isFocusedTextField = isFocusedTextField
                    )

                    if (index != (length - 1)) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }

            TextField(
                modifier = Modifier
                    .offset(-(size.spacing.max))
                    .focusRequester(focusRequester)
                    .onFocusChanged { focusState ->
                        isFocusedTextField = focusState.isFocused
                    },
                value = passcode,
                onValueChange = { newValue ->
                    if (newValue.length <= length && newValue.all { it.isDigit() }) {
                        passcode = newValue
                        onPasscodeChange(newValue)
                    }
                    passcode = newValue.take(length)
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
                style = style.small.regular,
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
    isFocusedTextField: Boolean = false,
) {
    val style = E2MTheme.typography
    val color = E2MTheme.alias.color
    val size = E2MTheme.alias.size

    Box(
        modifier = Modifier
            .size(size.icon.large)
            .clip(CircleShape)
            .background(
                if (isFocused && isFocusedTextField) color.surface.white else color.surface.textField
            )
            .border(
                width = size.stroke.thickX,
                color = if (isFocused && isFocusedTextField) color.border.blur2Light else color.border.textField,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = digit?.toString() ?: "_",
            style = style.h4.regular,
            color = if (isFocused && isFocusedTextField) color.text.blur2Light else color.text.black
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
