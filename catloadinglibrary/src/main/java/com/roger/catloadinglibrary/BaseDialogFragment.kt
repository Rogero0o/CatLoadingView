package com.roger.catloadinglibrary

import android.app.Activity
import android.content.DialogInterface
import android.os.Bundle
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import androidx.fragment.app.DialogFragment


open class BaseDialogFragment : DialogFragment(), View.OnTouchListener {
    private var cancelable = true

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val isShow = this.showsDialog
        this.showsDialog = false
        this.showsDialog = isShow
        val view: View? = view
        if (view != null) {
            check(view.parent == null) { "DialogFragment can not be attached to a container view" }
            this.dialog?.setContentView(view)
        }
        val activity: Activity? = activity
        if (activity != null) {
            this.dialog?.setOwnerActivity(activity)
        }
        this.dialog?.setOnCancelListener(null)
        this.dialog?.setOnDismissListener(null)
        this.dialog?.setCancelable(false)
        this.dialog?.window?.decorView?.setOnTouchListener(this)
        this.dialog?.setOnKeyListener(DialogInterface.OnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_ESCAPE) {
                dismiss()
                return@OnKeyListener true
            }
            false
        })
        if (savedInstanceState != null) {
            val dialogState = savedInstanceState.getBundle("android:savedDialogState")
            if (dialogState != null) {
                this.dialog!!.onRestoreInstanceState(dialogState)
            }
        }
    }

    override fun setCancelable(mCancelable: Boolean) {
        this.cancelable = mCancelable
    }


    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if (cancelable && dialog!!.isShowing) {
            dismiss()
            return true
        }
        return false
    }
}