package com.randybinondo.boardlottrainerforphstocks.ui.popup.helpers

import androidx.compose.ui.Alignment

fun getHorizontalOffset(
    isFitsStart: Boolean,
    isFitsEnd: Boolean,
    isFitsCenter: Boolean,
    endPlacementOffset: Int,
    startPlacementOffset: Int,
    centerPlacementOffset: Int)
: Pair<Alignment.Horizontal, Int> {
    // Check which alignment fits the best.
    val alignments = listOf(
        Alignment.Start,
        Alignment.CenterHorizontally,
        Alignment.End
    )

    // Check the corresponding offsets.
    val offsets = listOf(
        endPlacementOffset,
        centerPlacementOffset,
        startPlacementOffset
    )

    // Check which alignment and offset fits the best.
    val fallbacks = mutableListOf<Pair<Alignment.Horizontal, Int>>()
    for (index in 0..2) {
        if (listOf(isFitsEnd, isFitsCenter, isFitsStart)[index]) {
            fallbacks.add(Pair(alignments[index], offsets[index]))
        }
    }

    // If there is a fallback, choose it as the alignment and offset.
    val fallback = fallbacks.firstOrNull()
    if (fallback != null) {
        return fallback
    }

    // If there is no fallback, calculate the horizontal offset.
    val finalHorizontalFallback = if (isFitsStart) {
        0
    } else if (isFitsEnd) {
        2
    } else {
        1
    }
    val fallbackHorizontalOffset = offsets[finalHorizontalFallback]
    return Pair(alignments[finalHorizontalFallback], fallbackHorizontalOffset)
}