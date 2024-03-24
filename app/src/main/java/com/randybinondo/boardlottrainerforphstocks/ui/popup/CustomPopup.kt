package com.randybinondo.boardlottrainerforphstocks.ui.popup

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.randybinondo.boardlottrainerforphstocks.ui.popup.data.CustomPopupPositionProvider

@Composable
fun CustomPopup(
    popupState: PopupState,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    offset: DpOffset = DpOffset(0.dp, 0.dp),
    properties: PopupProperties = PopupProperties(focusable = true),
    content: @Composable ColumnScope.() -> Unit
) {
    // Create a transition state to track whether the popup is expanded.
    val expandedStates = remember { MutableTransitionState(false) }
    expandedStates.targetState = popupState.isVisible

    // Only show the popup if it's visible.
    if (expandedStates.currentState || expandedStates.targetState) {
        val density = LocalDensity.current

        // Instantiate a CustomPopupPositionProvider with the given offset.
        val popupPositionProvider = CustomPopupPositionProvider(
            contentOffset = offset,
            density = density
        ) { alignment, isTop ->
            // Update the PopupState's alignment and direction.
            popupState.horizontalAlignment = alignment
            popupState.isTop = !isTop
        }

        // Display the popup using the Popup composable.
        Popup(
            onDismissRequest = onDismissRequest,
            popupPositionProvider = popupPositionProvider,
            properties = properties
        ) {
            // Display the popup's content using the CustomPopupContent composable.
            CustomPopupContent(
                expandedStates = expandedStates,
                transformOrigin = TransformOrigin(
                    pivotFractionX = when (popupState.horizontalAlignment) {
                        Alignment.Start -> 0f
                        Alignment.CenterHorizontally -> 0.5f
                        else -> 1f
                    },
                    pivotFractionY = if (popupState.isTop) 1f else 0f
                ),
                modifier = modifier,
                content = content
            )
        }
    }
}


