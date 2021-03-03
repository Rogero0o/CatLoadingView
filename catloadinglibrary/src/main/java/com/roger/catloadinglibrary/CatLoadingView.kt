package com.roger.catloadinglibrary

import android.app.Dialog
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat

/**
 * Created by Administrator on 2016/3/30.
 */
class CatLoadingView : BaseDialogFragment() {
    private lateinit var operatingAnim: Animation
    private lateinit var eyeLeftAnim: Animation
    private lateinit var eyeRightAnim: Animation
    private lateinit var eyeLeft: View
    private lateinit var eyeRight: View
    private lateinit var eyelidLeft: EyelidView
    private lateinit var eyelidRight: EyelidView
    private lateinit var graduallyTextView: GraduallyTextView
    private lateinit var background: RelativeLayout
    private lateinit var mouse: View
    private var viewText: String? = null
    private var color = 0
    private var mainDialog: Dialog? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        mainDialog = Dialog(requireActivity(), R.style.cart_dialog).apply {
            setContentView(R.layout.catloading_main)
            window?.setGravity(Gravity.CENTER)
        }
        operatingAnim = RotateAnimation(
                360f, 0f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        )
        operatingAnim.repeatCount = Animation.INFINITE
        operatingAnim.duration = 2000
        eyeLeftAnim = RotateAnimation(
                360f, 0f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        )
        eyeLeftAnim.repeatCount = Animation.INFINITE
        eyeLeftAnim.duration = 2000
        eyeRightAnim = RotateAnimation(
                360f, 0f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        )
        eyeRightAnim.repeatCount = Animation.INFINITE
        eyeRightAnim.duration = 2000
        val lin = LinearInterpolator()
        operatingAnim.interpolator = lin
        eyeLeftAnim.interpolator = lin
        eyeRightAnim.interpolator = lin
        mainDialog?.window?.decorView?.let { view ->
            background = view.findViewById(R.id.background)
            if (color != 0) {
                background.setBackgroundColor(color)
            }
            mouse = view.findViewById(R.id.mouse)
            eyeLeft = view.findViewById(R.id.eye_left)
            eyeRight = view.findViewById(R.id.eye_right)
            eyelidLeft = view.findViewById<View>(R.id.eyelid_left) as EyelidView
            eyelidLeft.setColor(ContextCompat.getColor(requireContext(), R.color.eyelid))
            eyelidLeft.setFromFull(true)
            eyelidRight = view.findViewById<View>(R.id.eyelid_right) as EyelidView
            eyelidRight.setColor(ContextCompat.getColor(requireContext(), R.color.eyelid))
            eyelidRight.setFromFull(true)
            graduallyTextView =
                    view.findViewById<View>(R.id.graduallyTextView) as GraduallyTextView
            if (!TextUtils.isEmpty(viewText)) {
                graduallyTextView.setText(viewText)
            }
            operatingAnim.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {}
                override fun onAnimationEnd(animation: Animation) {}
                override fun onAnimationRepeat(animation: Animation) {
                    eyelidLeft.resetAnimator()
                    eyelidRight.resetAnimator()
                }
            })
        }
        return mainDialog!!
    }

    override fun onResume() {
        mouse.animation = operatingAnim
        eyeLeft.animation = eyeLeftAnim
        eyeRight.animation = eyeRightAnim
        eyelidLeft.startLoading()
        eyelidRight.startLoading()
        graduallyTextView.startLoading()
        super.onResume()
    }

    override fun onPause() {
        mouse.clearAnimation()
        eyeLeft.clearAnimation()
        eyeRight.clearAnimation()
        eyelidLeft.stopLoading()
        eyelidRight.stopLoading()
        graduallyTextView.stopLoading()
        super.onPause()
    }

    override fun onDestroyView() {
        if (mainDialog?.isShowing == true) {
            mainDialog?.dismiss()
            mainDialog = null
        }
        super.onDestroyView()
    }

    fun setText(labelText: String?) {
        viewText = labelText
    }

    fun setClickCancelAble(flag: Boolean) {
        this.isCancelable = flag
    }

    fun setBackgroundColor(color: Int) {
        this.color = color
    }
}