package com.dansdev.typingindicator.util

import android.content.res.Resources
import android.view.View

fun Int.dpToPx(resources: Resources): Int = (this * resources.displayMetrics.density).toInt()

fun View.scale(scale: Float) {
    scaleX = scale
    scaleY = scale
}