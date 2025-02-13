package com.mobile.e2m.core.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mobile.e2m.core.ui.theme.E2MTheme
import kotlinx.collections.immutable.ImmutableList

@Composable
fun <T> E2MSelectionLoad(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    numberElementsLoad: Int = 10,
    items: ImmutableList<T>,
    itemContentComposable: @Composable ((T) -> Unit) = { },
    itemLoadComposable: @Composable (() -> Unit) = { },
) {
    val size = E2MTheme.alias.size.spacing

    LazyColumn(
        modifier = modifier.padding(horizontal = size.small),
        contentPadding = PaddingValues(vertical = size.large),
        verticalArrangement = Arrangement.spacedBy(size.small2x),
    ) {
        if (isLoading) {
            items(numberElementsLoad) {
                itemLoadComposable()
            }
        } else {
            items(items) { item ->
                itemContentComposable(item)
            }
        }
    }
}
