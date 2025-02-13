package com.mobile.e2m.core.ui.theme.alias

import androidx.compose.ui.graphics.Color
import com.mobile.e2m.core.ui.theme.GradientPrimaryDefault
import com.mobile.e2m.core.ui.theme.global.E2MGlobalColor

data class E2MAliasColor(
    val surface: Surface,
    val text: Text,
    val border: Border,
    val icon: Icon,
    val gradientPrimaryDefault: List<Color>,
) {
    data class Surface(
        val white: Color,
        val black: Color,
        val whiteButtonFocus: Color,
        val blurButtonFocus: Color,
        val textField: Color,
        val button: Color,
        val backgroundDark: Color,
        val shadowDark: Color,
        val blur2Light: Color,
        val whiteDark: Color,
    )

    data class Text(
        val white: Color,
        val black: Color,
        val whiteDark: Color,
        val blurDark: Color,
        val textFieldPlaceholder: Color,
        val error: Color,
        val blur2Light: Color,
        val white200: Color,
        val white600: Color,
    )

    data class Border(
        val white: Color,
        val blurDark: Color,
        val textField: Color,
        val error: Color,
        val blur2Light: Color,
        val whiteDark: Color,
        val blackLight: Color,
    )

    data class Icon(
        val black: Color,
        val white: Color,
        val blue2Light: Color,
    )

    companion object {
        fun default(
            global: E2MGlobalColor = E2MGlobalColor.default()
        ) = E2MAliasColor(
            surface = Surface(
                white = global.write.color100,
                black = global.black.color100,
                whiteButtonFocus = global.write.color800,
                blurButtonFocus = global.blurDark.color800,
                textField = global.write.color400,
                button = global.write.color400,
                backgroundDark = global.black.color200,
                shadowDark = global.black.color800,
                blur2Light = global.blurLight.color800,
                whiteDark = global.write.color800,
            ),
            text = Text(
                white = global.write.color100,
                black = global.black.color100,
                whiteDark = global.write.color800,
                blurDark = global.blurDark.color100,
                textFieldPlaceholder = global.black.color800,
                error = global.red.color200,
                blur2Light = global.blurLight.color800,
                white200 = global.write.color200,
                white600 = global.write.color600,
            ),
            border = Border(
                white = global.write.color100,
                blurDark = global.blurDark.color100,
                textField = global.black.color800,
                error = global.red.color200,
                blur2Light = global.blurLight.color800,
                whiteDark = global.write.color800,
                blackLight = global.black.color800,
            ),
            icon = Icon(
                black = global.black.color100,
                white = global.write.color100,
                blue2Light = global.blurLight.color800,
            ),
            gradientPrimaryDefault = GradientPrimaryDefault
        )
    }
}
