package com.roger.catloadinglibrary

import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation

/**
 * Created by Administrator on 2016/3/30.
 */
class EyelidView : View {
    private lateinit var valueAnimator: ValueAnimator
    private lateinit var paint: Paint

    private var progress = 0f
    private var isLoading = false
    private var isStop = true
    private var duration = 1000
    private var isFromFull = false

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
        init()
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = Color.BLACK
        paint.style = Paint.Style.FILL
        background = null
        isFocusable = false
        isEnabled = false
        isFocusableInTouchMode = false
        valueAnimator = ValueAnimator.ofFloat(0f, 1f).setDuration(duration.toLong())
        valueAnimator.setInterpolator(AccelerateDecelerateInterpolator())
        valueAnimator.setRepeatCount(Animation.INFINITE)
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE)
        valueAnimator.addUpdateListener(
            AnimatorUpdateListener { animation ->
                progress = animation.animatedValue as Float
                invalidate()
            })
    }

    fun setColor(color: Int) {
        paint.color = color
    }

    fun startLoading() {
        if (!isStop) {
            return
        }
        isLoading = true
        isStop = false
        valueAnimator.start()
    }

    fun resetAnimator() {
        valueAnimator.start()
    }

    fun stopLoading() {
        isLoading = false
        valueAnimator.end()
        valueAnimator.cancel()
        isStop = true
    }

    fun setDuration(duration: Int) {
        this.duration = duration
    }

    override fun onVisibilityChanged(
        changedView: View,
        visibility: Int
    ) {
        super.onVisibilityChanged(changedView, visibility)
        if (!isLoading) {
            return
        }
        if (visibility == VISIBLE) {
            valueAnimator.resume()
        } else {
            valueAnimator.pause()
        }
    }

    fun setFromFull(fromFull: Boolean) {
        isFromFull = fromFull
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (!isStop) {
            var bottom = 0.0f
            bottom = if (!isFromFull) {
                progress * height
            } else {
                (1.0f - progress) * height
            }
            bottom = if (bottom >= height / 2) (height / 2).toFloat() else bottom
            canvas.drawRect(0f, 0f, width.toFloat(), bottom, paint)
        }
    }

    private fun whenStop(): Boolean {
        return !isLoading && progress <= 0.001f
    }
}