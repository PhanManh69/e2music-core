package com.mobile.e2m.core.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mobile.e2m.core.ui.composable.E2MButtonStyle.Gradient
import com.mobile.e2m.core.ui.composable.inputField.E2MIdentityPasscode
import com.mobile.e2m.core.ui.theme.E2MTheme

@Composable
fun E2MDialog(
    modifier: Modifier = Modifier,
    title: String? = null,
    content: @Composable () -> Unit = { },
    onDismissRequest: () -> Unit = { },
) {
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
        ),
    ) {
        Column(
            modifier = modifier
                .padding(horizontal = size.spacing.large)
                .shadowCustom(
                    color = color.surface.shadowDark,
                    shapeRadius = size.radius.radius7,
                    blurRadius = size.spacing.small4x
                )
                .background(
                    color = color.surface.backgroundDark,
                    shape = RoundedCornerShape(size.radius.radius7),
                )
                .border(
                    width = size.stroke.thick,
                    color = color.border.textField,
                    shape = RoundedCornerShape(size.radius.radius7)
                ),
        ) {
            title?.let {
                E2MHeader(
                    modifier = Modifier.padding(bottom = size.spacing.large),
                    title = it,
                    topPadding = 0.dp
                )
            }

            content()
        }
    }
}

@Preview
@Composable
fun DialogPreview() {
    E2MDialog(
        title = "Xác thực OTP",
        content = {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp).padding(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text(
                    text = "Mã xác thực OPT được gửi về email phankhacmanh.692003@gmail.com của bạn",
                    style = E2MTheme.typography.small.regular,
                    color = E2MTheme.alias.color.text.white,
                    textAlign = TextAlign.Center,
                )

                E2MIdentityPasscode(
                    length = 5,
                )

                E2MButton(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Button",
                    style = Gradient,
                )
            }
        }
    )
}
