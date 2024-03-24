package com.randybinondo.boardlottrainerforphstocks.ui.popup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun IconWithCustomPopup(
    popupState: PopupState = remember {
        PopupState(false)
    },
    text: String
) {
    Box {
        CustomPopup(
            popupState = popupState,
            onDismissRequest = {
                popupState.isVisible = false
            }
        ) {
            Text(
                text = text,
                modifier = Modifier.background(Color.White)
            )
        }
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = "Info Icon",
            modifier = Modifier.clickable {
                popupState.isVisible = !popupState.isVisible
            }
        )
    }
}