package com.mobile.e2m.core.ui.theme.global

import androidx.compose.ui.unit.Dp
import com.mobile.e2m.core.ui.theme.*

data class E2MGlobalSize(
    val size000: Dp,
    val size05: Dp,
    val size001: Dp,
    val size002: Dp,
    val size004: Dp,
    val size006: Dp,
    val size008: Dp,
    val size012: Dp,
    val size016: Dp,
    val size020: Dp,
    val size024: Dp,
    val size032: Dp,
    val size036: Dp,
    val size040: Dp,
    val size048: Dp,
    val size064: Dp,
    val size080: Dp,
    val size096: Dp,
    val size128: Dp,
    val size144: Dp,
    val size160: Dp,
    val size224: Dp,
    val size240: Dp,
    val size320: Dp,
    val size400: Dp,
    val sizeMax: Dp,
) {
    companion object {
        fun default() = E2MGlobalSize(
            size000 = Size000,
            size05 = Size05,
            size001 = Size001,
            size002 = Size002,
            size004 = Size004,
            size006 = Size006,
            size008 = Size008,
            size012 = Size012,
            size016 = Size016,
            size020 = Size020,
            size024 = Size024,
            size032 = Size032,
            size036 = Size036,
            size040 = Size040,
            size048 = Size048,
            size064 = Size064,
            size080 = Size080,
            size096 = Size096,
            size128 = Size128,
            size144 = Size144,
            size160 = Size160,
            size224 = Size224,
            size240 = Size240,
            size320 = Size320,
            size400 = Size400,
            sizeMax = SizeMax,
        )
    }
}
