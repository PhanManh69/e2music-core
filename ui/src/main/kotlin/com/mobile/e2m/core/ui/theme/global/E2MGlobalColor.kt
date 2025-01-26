package com.mobile.e2m.core.ui.theme.global

import androidx.compose.ui.graphics.Color
import com.mobile.e2m.core.ui.theme.*

data class E2MGlobalColor(
    val black: BaseColor,
    val write: BaseColor,
    val blurDark: BaseColor,
    val blurLight: BaseColor,
    val red: BaseColor,
) {
    data class BaseColor(
        val color100: Color,
        val color200: Color,
        val color400: Color,
        val color600: Color,
        val color800: Color,
    )

    companion object {
        fun default() = E2MGlobalColor(
            black = BaseColor(
                color100 = Black100,
                color200 = Black200,
                color400 = Black400,
                color600 = Black600,
                color800 = Black800,
            ),
            write = BaseColor(
                color100 = Write100,
                color200 = Write200,
                color400 = Write400,
                color600 = Write600,
                color800 = Write800,
            ),
            blurDark = BaseColor(
                color100 = BlurDark100,
                color200 = BlurDark200,
                color400 = BlurDark400,
                color600 = BlurDark600,
                color800 = BlurDark800,
            ),
            blurLight = BaseColor(
                color100 = BlurLight100,
                color200 = BlurLight200,
                color400 = BlurLight400,
                color600 = BlurLight600,
                color800 = BlurLight800,
            ),
            red = BaseColor(
                color100 = Red100,
                color200 = Red200,
                color400 = Red400,
                color600 = Red600,
                color800 = Red800,
            )
        )
    }
}
