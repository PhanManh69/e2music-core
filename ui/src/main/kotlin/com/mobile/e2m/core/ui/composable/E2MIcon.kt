package com.mobile.e2m.core.ui.composable

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobile.e2m.core.ui.R
import com.mobile.e2m.core.ui.theme.E2MTheme

@Composable
fun E2MIcon(
    modifier: Modifier = Modifier,
    iconId: Int? = null,
    contentDescription: String? = null,
    tint: Color = E2MTheme.alias.color.icon.white,
) {
    iconId?.let {
        Icon(
            modifier = modifier,
            painter = painterResource(id = iconId),
            contentDescription = contentDescription,
            tint = tint,
        )
    }
}

@Preview
@Composable
fun E2MIconPreview() {
    E2MIcon(
        modifier = Modifier.size(32.dp),
        iconId = R.drawable.ic_home,
    )
}
