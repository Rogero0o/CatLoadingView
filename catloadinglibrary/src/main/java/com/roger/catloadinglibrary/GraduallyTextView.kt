package com.roger.catloadinglibrary

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import androidx.appcompat.widget.AppCompatEditText

/**
 * Created by roger.luo on 2016/3/30.
 */
class GraduallyTextView : AppCompatEditText {
    private lateinit var paint: Paint
    private lateinit var valueAnimator: ValueAnimator

    private lateinit var charSequence: CharSequence
    private var startY = 0
    private var progress = 0f
    private var isLoading = false
    private var textLength = 0
    private var isStop = true
    private var localScaleX = 0f
    private var duration = 2000
    private var singleDuration = 0f


    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
        init()
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.style = Paint.Style.FILL
        background = null
        isCursorVisible = false
        isFocusable = false
        isEnabled = false
        isFocusableInTouchMode = false
        valueAnimator = ValueAnimator.ofFloat(0f, 100f).setDuration(duration.toLong())
        valueAnimator.interpolator = AccelerateDecelerateInterpolator()
        valueAnimator.repeatCount = Animation.INFINITE
        valueAnimator.repeatMode = ValueAnimator.RESTART
        valueAnimator.addUpdateListener { animation ->
            progress = animation.animatedValue as Float
            this@GraduallyTextView.invalidate()
        }
    }

    fun startLoading() {
        if (!isStop) {
            return
        }
        textLength = text!!.length
        if (text.toString().isEmpty()) {
            return
        }
        isLoading = true
        isStop = false
        charSequence = text!!
        localScaleX = textScaleX * 10
        startY = lineHeight
        paint.color = currentTextColor
        paint.textSize = textSize
        minWidth = width
        setText("")
        hint = ""
        valueAnimator.start()
        singleDuration = 100f / textLength
    }

    fun stopLoading() {
        isLoading = false
        valueAnimator.end()
        valueAnimator.cancel()
        isStop = true
        setText(charSequence)
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
        if (visibility == View.VISIBLE) {
            valueAnimator.resume()
        } else {
            valueAnimator.pause()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (!isStop) {
            paint.alpha = 255
            if (progress / singleDuration >= 1) {
                canvas.drawText(
                    charSequence.toString(), 0,
                    (progress / singleDuration).toInt(), localScaleX, startY.toFloat(),
                    paint
                )
            }
            paint.alpha = (255 * (progress % singleDuration / singleDuration)).toInt()
            val lastOne = (progress / singleDuration).toInt()
            if (lastOne < textLength) {
                canvas.drawText(
                    charSequence[lastOne].toString(), 0, 1,
                    localScaleX + paint.measureText(
                        charSequence.subSequence(0, lastOne).toString()
                    ),
                    height / 2.toFloat(), paint
                )
            }
        }
    }
}