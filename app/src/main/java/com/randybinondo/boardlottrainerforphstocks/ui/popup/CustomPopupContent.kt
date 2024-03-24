package com.randybinondo.boardlottrainerforphstocks.ui.popup

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun CustomPopupContent(
    expandedStates: MutableTransitionState<Boolean>,
    transformOrigin: TransformOrigin,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    // Menu open/close animation.
    val transition = updateTransition(expandedStates, "Popup")

    // Scale animation.
    val scale by transition.animateFloat(
        transitionSpec = {
            if (false isTransitioningTo true) {
                // Dismissed to expanded.
                tween(durationMillis = 200)
            } else {
                // Expanded to dismissed.
                tween(durationMillis = 200)
            }
        },
        label = "Popup Scale"
    ) {
        if (it) {
            // Popup is expanded.
            1f
        } else {
            // Popup is dismissed.
            0f
        }
    }

    // Alpha animation.
    val alpha by transition.animateFloat(
        transitionSpec = {
            if (false isTransitioningTo true) {
                // Dismissed to expanded.
                tween(durationMillis = 200)
            } else {
                // Expanded to dismissed.
                tween(durationMillis = 200)
            }
        },
        label = "Popup Alpha"
    ) {
        if (it) {
            // Popup is expanded.
            1f
        } else {
            // Popup is dismissed.
            0f
        }
    }

    // Helper function for applying animations to graphics layer.
    fun GraphicsLayerScope.graphicsLayerAnim() {
        scaleX = scale
        scaleY = scale
        this.alpha = alpha
        this.transformOrigin = transformOrigin
    }

    Surface(
        modifier = Modifier
            .graphicsLayer {
                graphicsLayerAnim()
            }
    ) {
        Column(
            modifier = modifier
                .width(IntrinsicSize.Max)
                .verticalScroll(rememberScrollState()),
            content = content
        )
    }
}