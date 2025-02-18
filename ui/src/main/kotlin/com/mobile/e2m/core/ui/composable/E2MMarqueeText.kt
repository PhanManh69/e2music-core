package com.mobile.e2m.core.ui.composable

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.TargetBasedAnimation
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.mobile.e2m.core.ui.theme.E2MTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private enum class MarqueeLayers { MainText, SecondaryText, EdgesGradient }
private data class TextLayoutInfo(val textWidth: Int, val containerWidth: Int)

@Composable
fun E2MMarqueeText(
    text: String,
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    gradientEdgeColor: Color = Color.White,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current,
) {
    val scope = rememberCoroutineScope()
    val textLayoutInfoState = remember { mutableStateOf<TextLayoutInfo?>(null) }
    val createText = @Composable { localModifier: Modifier ->
        Text(
            text,
            textAlign = textAlign,
            modifier = localModifier,
            color = color,
            fontSize = fontSize,
            fontStyle = fontStyle,
            fontWeight = fontWeight,
            fontFamily = fontFamily,
            letterSpacing = letterSpacing,
            textDecoration = textDecoration,
            lineHeight = lineHeight,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = 1,
            onTextLayout = onTextLayout,
            style = style,
        )
    }

    var offset by remember { mutableIntStateOf(0) }
    var hasGradientEdge by remember { mutableStateOf(false) }

    LaunchedEffect(textLayoutInfoState.value) {
        val textLayoutInfo = textLayoutInfoState.value ?: return@LaunchedEffect
        val duration = 7500 * textLayoutInfo.textWidth / textLayoutInfo.containerWidth
        val delay = 2000L

        scope.launch {
            delay(delay)
            hasGradientEdge = true
        }

        if (textLayoutInfo.textWidth <= textLayoutInfo.containerWidth) return@LaunchedEffect

        do {
            val startTime = withFrameNanos { it }
            val animation = TargetBasedAnimation(
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = duration,
                        delayMillis = delay.toInt(),
                        easing = LinearEasing,
                    ),
                    repeatMode = RepeatMode.Restart
                ),
                typeConverter = Int.VectorConverter,
                initialValue = 0,
                targetValue = -textLayoutInfo.textWidth
            )

            do {
                val playTime = withFrameNanos { it } - startTime
                offset = (animation.getValueFromNanos(playTime))
            } while (!animation.isFinishedFromNanos(playTime))

            delay(delay)
        } while (true)
    }

    SubcomposeLayout(
        modifier = modifier.clipToBounds()
    ) { constraints ->
        val infiniteWidthConstraints = constraints.copy(maxWidth = Int.MAX_VALUE)

        var gradient: Placeable? = null
        var secondPlaceableWithOffset: Pair<Placeable, Int>? = null
        var mainText = subcompose(MarqueeLayers.MainText) {
            createText(textModifier)
        }.first().measure(infiniteWidthConstraints)

        if (mainText.width <= constraints.maxWidth) {
            mainText = subcompose(MarqueeLayers.SecondaryText) {
                createText(textModifier.fillMaxWidth())
            }.first().measure(constraints)

            textLayoutInfoState.value = null
        } else {
            val spacing = constraints.maxWidth * 2 / 3
            val secondTextOffset = mainText.width + offset + spacing
            val secondTextSpace = constraints.maxWidth - secondTextOffset

            textLayoutInfoState.value = TextLayoutInfo(
                textWidth = mainText.width + spacing,
                containerWidth = constraints.maxWidth
            )

            if (secondTextSpace > 0) {
                secondPlaceableWithOffset = subcompose(MarqueeLayers.SecondaryText) {
                    createText(textModifier)
                }.first().measure(infiniteWidthConstraints) to secondTextOffset
            }

            gradient = subcompose(MarqueeLayers.EdgesGradient) {
                Row {
                    if (hasGradientEdge) {
                        E2MGradientEdge(gradientEdgeColor, Color.Transparent)
                    }

                    Spacer(Modifier.weight(1f))

                    E2MGradientEdge(Color.Transparent, gradientEdgeColor)
                }
            }.first().measure(constraints.copy(maxHeight = mainText.height))
        }

        layout(
            width = constraints.maxWidth,
            height = mainText.height
        ) {
            mainText.place(offset, 0)
            secondPlaceableWithOffset?.let {
                it.first.place(it.second, 0)
            }
            gradient?.place(0, 0)
        }
    }
}

@Composable
private fun E2MGradientEdge(
    startColor: Color,
    endColor: Color,
) {
    val size = E2MTheme.alias.size

    Box(
        modifier = Modifier
            .width(size.spacing.small2x)
            .fillMaxHeight()
            .background(
                brush = Brush.horizontalGradient(
                    0f to startColor, 1f to endColor,
                )
            )
    )
}

