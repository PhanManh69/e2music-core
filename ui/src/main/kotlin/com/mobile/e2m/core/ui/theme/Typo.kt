package com.mobile.e2m.core.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mobile.e2m.core.ui.R

val E2MFontFamily = FontFamily(
    Font(R.font.source_code_pro_black, FontWeight.Black),
    Font(R.font.source_code_pro_bold, FontWeight.Bold),
    Font(R.font.source_code_pro_medium, FontWeight.Medium),
    Font(R.font.source_code_pro_light, FontWeight.Light),
)

data class E2MTypography(
    val h1: H1,
    val h2: H2,
    val h3: H3,
    val h4: H4,
    val title: Title,
    val base: Base,
    val small: Small,
    val caption: Caption,
) {
    data class H1(
        val bold: TextStyle,
        val black: TextStyle,
    )

    data class H2(
        val bold: TextStyle,
        val medium: TextStyle,
        val semiBold: TextStyle,
    )

    data class H3(
        val bold: TextStyle,
        val medium: TextStyle,
        val semiBold: TextStyle,
    )

    data class H4(
        val bold: TextStyle,
        val medium: TextStyle,
        val semiBold: TextStyle,
    )

    data class Title(
        val regular: TextStyle,
        val bold: TextStyle,
        val medium: TextStyle,
        val semiBold: TextStyle,
        val black: TextStyle,
    )

    data class Base(
        val regular: TextStyle,
        val medium: TextStyle,
        val semiBold: TextStyle,
        val bold: TextStyle,
    )

    data class Small(
        val regular: TextStyle,
        val medium: TextStyle,
        val semiBold: TextStyle,
    )

    data class Caption(
        val regular: TextStyle,
        val medium: TextStyle,
    )

    companion object {
        fun default() = E2MTypography(
            h1 = H1(
                bold = TextStyle(
                    fontFamily = E2MFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    lineHeight = 40.sp,
                    letterSpacing = (-0.64).sp
                ),
                black = TextStyle(
                    fontFamily = E2MFontFamily,
                    fontWeight = FontWeight.Black,
                    fontSize = 32.sp,
                    lineHeight = 40.sp,
                    letterSpacing = (-1.28).sp
                ),
            ),
            h2 = H2(
                bold = TextStyle(
                    fontFamily = E2MFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp,
                    lineHeight = 36.sp,
                    letterSpacing = (-0.52).sp
                ),
                semiBold = TextStyle(
                    fontFamily = E2MFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 26.sp,
                    lineHeight = 36.sp,
                    letterSpacing = (-1.04).sp
                ),
                medium = TextStyle(
                    fontFamily = E2MFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 26.sp,
                    lineHeight = 36.sp,
                    letterSpacing = (-1.04).sp
                ),
            ),
            h3 = H3(
                bold = TextStyle(
                    fontFamily = E2MFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    lineHeight = 32.sp,
                    letterSpacing = (-0.66).sp,
                ),
                semiBold = TextStyle(
                    fontFamily = E2MFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 22.sp,
                    lineHeight = 32.sp,
                    letterSpacing = (-0.88).sp,
                ),
                medium = TextStyle(
                    fontFamily = E2MFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 22.sp,
                    lineHeight = 32.sp,
                    letterSpacing = (-0.88).sp,
                ),
            ),
            h4 = H4(
                bold = TextStyle(
                    fontFamily = E2MFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    lineHeight = 28.sp,
                    letterSpacing = (-0.3).sp,
                ),
                semiBold = TextStyle(
                    fontFamily = E2MFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    lineHeight = 28.sp,
                    letterSpacing = (-0.5).sp,
                ),
                medium = TextStyle(
                    fontFamily = E2MFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    lineHeight = 28.sp,
                    letterSpacing = (-0.6).sp,
                ),
            ),
            title = Title(
                regular = TextStyle(
                    fontFamily = E2MFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                    lineHeight = 24.sp,
                    letterSpacing = (-0.27).sp,
                ),
                bold = TextStyle(
                    fontFamily = E2MFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    lineHeight = 24.sp,
                    letterSpacing = (-0.27).sp,
                ),
                semiBold = TextStyle(
                    fontFamily = E2MFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    lineHeight = 24.sp,
                    letterSpacing = (-0.27).sp,
                ),
                medium = TextStyle(
                    fontFamily = E2MFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    lineHeight = 24.sp,
                    letterSpacing = (-0.27).sp,
                ),
                black = TextStyle(
                    fontFamily = E2MFontFamily,
                    fontWeight = FontWeight.Black,
                    fontSize = 24.sp,
                    lineHeight = 24.sp,
                    letterSpacing = (-0.27).sp,
                ),
            ),
            base = Base(
                regular = TextStyle(
                    fontFamily = E2MFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    letterSpacing = (-0.32).sp,
                ),
                medium = TextStyle(
                    fontFamily = E2MFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    letterSpacing = (-0.32).sp,
                ),
                semiBold = TextStyle(
                    fontFamily = E2MFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    letterSpacing = (-0.32).sp,
                ),
                bold = TextStyle(
                    fontFamily = E2MFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    letterSpacing = (-0.32).sp,
                ),
            ),
            small = Small(
                regular = TextStyle(
                    fontFamily = E2MFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    letterSpacing = (-0.28).sp,
                ),
                medium = TextStyle(
                    fontFamily = E2MFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    letterSpacing = (-0.28).sp,
                ),
                semiBold = TextStyle(
                    fontFamily = E2MFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    letterSpacing = (-0.28).sp,
                ),
            ),
            caption = Caption(
                regular = TextStyle(
                    fontFamily = E2MFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    letterSpacing = (-0.36).sp,
                ),
                medium = TextStyle(
                    fontFamily = E2MFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    letterSpacing = (-0.36).sp,
                ),
            ),
        )
    }
}

val LocalE2MTypography = compositionLocalOf {
    E2MTypography.default()
}
