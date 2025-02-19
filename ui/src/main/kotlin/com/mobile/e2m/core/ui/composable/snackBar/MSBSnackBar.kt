package com.mobile.e2m.core.ui.composable.snackBar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobile.e2m.core.ui.R
import com.mobile.e2m.core.ui.theme.E2MTheme

@Stable
data class E2MSnackBarStyle(
    val backgroundColor: Color,
    @DrawableRes val iconId: Int,
)

@Composable
fun E2MSnackBar(
    modifier: Modifier = Modifier,
    snackBarData: SnackbarData,
) {
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color
    val visuals = snackBarData.visuals
    val type = remember {
        if (visuals is E2MSnackBarVisuals) {
            visuals.status
        } else {
            E2MSnackBarStatus.Success
        }
    }

    val style = when (type) {
        E2MSnackBarStatus.Error -> E2MSnackBarStyle(
            backgroundColor = color.surface.red200,
            iconId = R.drawable.ic_cross_circle,
        )

        else -> E2MSnackBarStyle(
            backgroundColor = color.surface.green200,
            iconId = R.drawable.ic_check_circle,
        )
    }

    Surface(
        modifier = modifier.fillMaxWidth(),
        color = style.backgroundColor,
        shape = RoundedCornerShape(size.radius.radius2),
    ) {
        Row(
            modifier = Modifier.padding(
                vertical = size.spacing.small,
                horizontal = size.spacing.large,
            ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(style.iconId),
                tint = color.icon.white,
                contentDescription = null,
            )

            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = size.spacing.large),
                text = snackBarData.visuals.message,
                color = color.text.white,
                style = E2MTheme.typography.base.medium,
            )

            IconButton(
                modifier = Modifier.size(size.icon.small1X),
                onClick = {
                    snackBarData.dismiss()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    tint = color.icon.white,
                    contentDescription = null,
                )
            }
        }
    }
}

@Preview
@Composable
fun Preview_E2MSnackBar_Success() {
    E2MSnackBar(
        snackBarData = E2MSnackBarDataPreviewImpl(
            status = E2MSnackBarStatus.Success,
        ),
    )
}

@Preview
@Composable
fun Preview_E2MSnackBar_Fail() {
    E2MSnackBar(
        snackBarData = E2MSnackBarDataPreviewImpl(
            status = E2MSnackBarStatus.Error,
        ),
    )
}

private class E2MSnackBarDataPreviewImpl(
    val status: E2MSnackBarStatus,
) : SnackbarData {
    override val visuals: SnackbarVisuals
        get() = E2MSnackBarVisuals(
            message = "mess",
            status = status,
        )

    override fun dismiss() {}

    override fun performAction() {}
}
