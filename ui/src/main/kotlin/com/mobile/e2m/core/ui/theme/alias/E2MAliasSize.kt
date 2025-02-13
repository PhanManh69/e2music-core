package com.mobile.e2m.core.ui.theme.alias

import androidx.compose.ui.unit.Dp
import com.mobile.e2m.core.ui.theme.global.E2MGlobalSize

data class E2MAliasSize(
    val radius: Radius,
    val stroke: Stroke,
    val spacing: Spacing,
    val icon: Icon,
) {
    data class Radius(
        val none: Dp,
        val radius1: Dp,
        val radius2: Dp,
        val radius3: Dp,
        val radius4: Dp,
        val radius5: Dp,
        val radius6: Dp,
        val radius7: Dp,
    )

    data class Stroke(
        val thin: Dp,
        val thick: Dp,
        val thickX: Dp,
    )

    data class Spacing(
        val none: Dp,
        val small4x: Dp,
        val small3x: Dp,
        val small2x: Dp,
        val smallX: Dp,
        val small: Dp,
        val medium: Dp,
        val large: Dp,
        val largeX: Dp,
        val large2X: Dp,
        val large3X: Dp,
        val large4x: Dp,
        val large5x: Dp,
        val large6x: Dp,
        val large7x: Dp,
        val max: Dp,
    )

    data class Icon(
        val small4X: Dp,
        val small3X: Dp,
        val small2X: Dp,
        val small1X: Dp,
        val smallX: Dp,
        val small: Dp,
        val medium: Dp,
        val large: Dp,
        val largeX: Dp,
        val large1X: Dp,
    )

    companion object {
        fun default(
            global: E2MGlobalSize = E2MGlobalSize.default()
        ) = E2MAliasSize(
            radius = Radius(
                none = global.size000,
                radius1 = global.size004,
                radius2 = global.size008,
                radius3 = global.size012,
                radius4 = global.size016,
                radius5 = global.size020,
                radius6 = global.size024,
                radius7 = global.size032,
            ),
            stroke = Stroke(
                thin = global.size05,
                thick = global.size001,
                thickX = global.size002,
            ),
            spacing = Spacing(
                none = global.size000,
                small4x = global.size004,
                small3x = global.size006,
                small2x = global.size008,
                smallX = global.size012,
                small = global.size016,
                medium = global.size020,
                large = global.size024,
                largeX = global.size032,
                large2X = global.size040,
                large3X = global.size048,
                large4x = global.size064,
                large5x = global.size096,
                large6x = global.size160,
                large7x = global.size128,
                max = global.sizeMax,
            ),
            icon = Icon(
                small4X = global.size008,
                small3X = global.size016,
                small2X = global.size020,
                small1X = global.size024,
                smallX = global.size032,
                small = global.size036,
                medium = global.size040,
                large = global.size048,
                largeX = global.size128,
                large1X = global.size080,
            ),
        )
    }
}
