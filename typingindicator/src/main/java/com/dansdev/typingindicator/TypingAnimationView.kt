package com.dansdev.typingindicator

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import com.dansdev.typingindicator.util.dpToPx
import com.dansdev.typingindicator.util.scale

class TypingAnimationView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    companion object {

        const val DEFAULT_CIRCLES_COUNT = 3
        const val DEFAULT_CIRCLE_DURATION = 300L
        const val DEFAULT_START_ANIM_CIRCLE_DURATION = 200L
        const val DEFAULT_ANIM_SCALE_STEP = 1f
        const val DEFAULT_MARGIN_CIRCLES = 2
    }

    @DrawableRes
    var imageCircle: Int = R.drawable.image_circle_typing
    var circleCount = DEFAULT_CIRCLES_COUNT
    var circleAnimDuration = DEFAULT_CIRCLE_DURATION
    var startCircleAnimDuration = DEFAULT_START_ANIM_CIRCLE_DURATION
    var scaleStep = DEFAULT_ANIM_SCALE_STEP
    var stepBetweenCircleDp = DEFAULT_MARGIN_CIRCLES.dpToPx(resources)
    var autoStartByAttachToWindow = true

    private val circles = mutableListOf<View>()
    private val animators = mutableListOf<Animator>()

    init {
        orientation = LinearLayout.HORIZONTAL
        gravity = Gravity.CENTER
        setCircles(circleCount)
    }

    fun setCircles(circleCount: Int) {
        circles.clear()
        removeAllViews()
        for (i in 0 until circleCount) {
            val view = obtainCircle(imageCircle)
            addView(view)
            circles.add(view)
        }
    }

    private fun obtainCircle(@DrawableRes imageCircle: Int): View {
        val image = AppCompatImageView(context)
        image.id = View.generateViewId()
        val params = ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        params.setMargins(stepBetweenCircleDp, stepBetweenCircleDp, stepBetweenCircleDp, stepBetweenCircleDp)
        image.layoutParams = params
        image.setImageResource(imageCircle)
        image.adjustViewBounds = false
        return image
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (autoStartByAttachToWindow) {
            startCircleAnimation()
        }
    }

    fun startCircleAnimation() {
        circles.forEachIndexed { index, circle -> animateCircle(index, circle) }
    }

    private fun animateCircle(index: Int, circle: View) {
        val animator = ValueAnimator.ofFloat(1f, 1f + scaleStep)
        animator.duration = circleAnimDuration
        animator.startDelay = startCircleAnimDuration * index
        animator.repeatCount = ValueAnimator.INFINITE
        animator.repeatMode = ValueAnimator.REVERSE
        animator.addUpdateListener {
            val scale = it.animatedValue.toString().toFloat()
            circle.scale(scale)
        }
        animator.start()

        animators.add(animator)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        if (autoStartByAttachToWindow) {
            stopCircleAnimation()
        }
    }

    fun stopCircleAnimation() {
        animators.forEach { it.cancel() }
        animators.clear()
    }
}