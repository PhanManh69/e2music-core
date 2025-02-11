package com.mobile.e2m.core.ui.composable.inputField

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobile.e2m.core.ui.composable.shadowCustom
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.core.ui.util.ValueConfig.MAX_LINE

@Composable
fun E2MTextField(
    modifier: Modifier = Modifier,
    initText: String = "",
    placeholder: String = "",
    caption: String? = null,
    iconId: Int? = null,
    maxLines: Int = MAX_LINE,
    onValueChange: ((String) -> Unit)? = null,
    onFocusChanged: ((FocusState) -> Unit)? = null,
    trailingIconOnClick: () -> Unit = { },
    textStyle: TextStyle = E2MTheme.typography.title.regular,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    val isFocused: MutableState<Boolean> = remember { mutableStateOf(false) }
    val style = E2MTheme.typography
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color
    val borderColor = when {
        isFocused.value && caption.isNullOrEmpty() -> color.border.blurDark
        !caption.isNullOrEmpty() -> color.border.error
        else -> color.border.textField
    }

    Column {
        E2MCustomTextField(
            modifier = modifier
                .fillMaxWidth()
                .shadowCustom(
                    color = color.surface.shadowDark,
                    shapeRadius = size.radius.radius7,
                    blurRadius = size.spacing.small4x
                )
                .background(
                    color = color.surface.textField,
                    shape = RoundedCornerShape(size.radius.radius7),
                )
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(size.radius.radius7)
                )
                .onFocusChanged { focusState ->
                    isFocused.value = focusState.hasFocus
                    onFocusChanged?.invoke(focusState)
                },
            value = remember { mutableStateOf(initText) },
            onValueChange = onValueChange,
            textStyle = textStyle,
            maxLines = maxLines,
            singleLine = true,
            contentPadding = PaddingValues(horizontal = size.spacing.large),
            visualTransformation = visualTransformation,
            shape = RoundedCornerShape(size.radius.radius7),
            placeholder = {
                Text(
                    text = placeholder,
                    style = style.title.regular,
                    color = color.text.textFieldPlaceholder
                )
            },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                cursorColor = color.text.black,
            ),
            trailingIcon = {
                if (iconId != null)
                    IconButton(
                        modifier = Modifier.padding(end = size.spacing.small),
                        onClick = { trailingIconOnClick() }
                    ) {
                        Icon(
                            modifier = Modifier.size(size.icon.smallX),
                            painter = painterResource(id = iconId),
                            contentDescription = null,
                            tint = color.icon.black
                        )
                    }
            },
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
        )

        caption?.let {
            Text(
                modifier = Modifier.padding(
                    start = size.spacing.small2x,
                    top = size.spacing.smallX
                ),
                text = caption,
                style = style.small.regular,
                color = color.text.error,
            )
        }
    }
}

@Preview
@Composable
fun E2MTextFieldPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        E2MTextField(
            placeholder = "Nhập tài khoản hoặc email"
        )

        E2MTextField(
            placeholder = "Nhập tài khoản hoặc email",
            caption = "Error"
        )
    }
}
