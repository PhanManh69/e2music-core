package com.mobile.e2m.core.ui.composable

import java.util.Collections

fun <T> MutableList<T>.swap(fromIndex: Int, toIndex: Int) {
    if (fromIndex in indices && toIndex in indices) {
        Collections.swap(this, fromIndex, toIndex)
    }
}
