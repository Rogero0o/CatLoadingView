package com.roger.catloadinglibrary;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

import androidx.fragment.app.DialogFragment;

/**
 * Created by Administrator on 2016/3/30.
 */
public class CatLoadingView extends DialogFragment {

    public CatLoadingView() {
    }

    Animation operatingAnim, eye_left_Anim, eye_right_Anim;

    Dialog mDialog;

    View mouse, eye_left, eye_right;

    EyelidView eyelid_left, eyelid_right;

    GraduallyTextView mGraduallyTextView;

    RelativeLayout background;

    String text;

    int color;

    private boolean isClickCancelAble = true;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (mDialog == null) {
            mDialog = new Dialog(getActivity(), R.style.cart_dialog);
            mDialog.setContentView(R.layout.catloading_main);
            mDialog.setCanceledOnTouchOutside(isClickCancelAble);
            mDialog.getWindow().setGravity(Gravity.CENTER);

            operatingAnim = new RotateAnimation(360f, 0f, Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            operatingAnim.setRepeatCount(Animation.INFINITE);
            operatingAnim.setDuration(2000);

            eye_left_Anim = new RotateAnimation(360f, 0f, Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            eye_left_Anim.setRepeatCount(Animation.INFINITE);
            eye_left_Anim.setDuration(2000);

            eye_right_Anim = new RotateAnimation(360f, 0f, Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            eye_right_Anim.setRepeatCount(Animation.INFINITE);
            eye_right_Anim.setDuration(2000);

            LinearInterpolator lin = new LinearInterpolator();
            operatingAnim.setInterpolator(lin);
            eye_left_Anim.setInterpolator(lin);
            eye_right_Anim.setInterpolator(lin);

            View view = mDialog.getWindow().getDecorView();

            background = view.findViewById(R.id.background);

            if (color !=0) {
                background.setBackgroundColor(color);
            }

            mouse = view.findViewById(R.id.mouse);

            eye_left = view.findViewById(R.id.eye_left);

            eye_right = view.findViewById(R.id.eye_right);

            eyelid_left = (EyelidView) view.findViewById(R.id.eyelid_left);

            eyelid_left.setColor(Color.parseColor("#d0ced1"));

            eyelid_left.setFromFull(true);

            eyelid_right = (EyelidView) view.findViewById(R.id.eyelid_right);

            eyelid_right.setColor(Color.parseColor("#d0ced1"));

            eyelid_right.setFromFull(true);

            mGraduallyTextView = (GraduallyTextView) view.findViewById(R.id.graduallyTextView);

            if (!TextUtils.isEmpty(text)) {
                mGraduallyTextView.setText(text);
            }

            operatingAnim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    eyelid_left.resetAnimator();
                    eyelid_right.resetAnimator();
                }
            });
        }
        return mDialog;
    }

    @Override
    public void onResume() {
        super.onResume();
        mouse.setAnimation(operatingAnim);
        eye_left.setAnimation(eye_left_Anim);
        eye_right.setAnimation(eye_right_Anim);
        eyelid_left.startLoading();
        eyelid_right.startLoading();
        mGraduallyTextView.startLoading();
    }

    @Override
    public void onPause() {
        super.onPause();

        operatingAnim.reset();
        eye_left_Anim.reset();
        eye_right_Anim.reset();

        mouse.clearAnimation();
        eye_left.clearAnimation();
        eye_right.clearAnimation();

        eyelid_left.stopLoading();
        eyelid_right.stopLoading();
        mGraduallyTextView.stopLoading();
    }

    public void setText(String str) {
        text = str;
    }

    public void setClickCancelAble(boolean bo){
        isClickCancelAble = bo;
    }

    public void setBackgroundColor(int color){
        this.color = color;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        mDialog = null;
        System.gc();
    }
}