package com.yufan.library.util;

import android.animation.TimeInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;

import com.yufan.library.R;
import com.yufan.library.view.anim.ElasticityInterpolator;


/**
 * Created by mengfantao on 17/4/26.
 */

public class AnimateUtil {
    public static TimeInterpolator interpolator= new DecelerateInterpolator();
    public  static ElasticityInterpolator interpolator2=new ElasticityInterpolator();

    /**
     * 缩小到0.3
     * 放大到1.2
     * 缩小到1
     * 一级页面的底部tab动画
     * @param view
     */
    public static void onClickMainAnimate(final View view){
        view.clearAnimation();
        view.animate().alpha(0).setDuration(10);
        view.animate().scaleX(0.5f).scaleY(0.5f).setDuration(10).setInterpolator(interpolator).withEndAction(new Runnable() {
            @Override
            public void run() {
                view.animate().alpha(1).setDuration(30);
                view.animate().scaleX(1f).scaleY(1f).setDuration(360).setInterpolator(interpolator2);
            }
        });


    }

    /**
     * 收藏动画
     * @param view
     */
    public static void onFavoritesAnimate(final View view){
        view.setAlpha(0f);
        view.setScaleY(1.6f);
        view.setScaleX(1.6f);
        view.animate().scaleX(0.9f).scaleY(0.9f).setDuration(400).setInterpolator(AnimateUtil.interpolator).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        view.animate().scaleX(1).scaleY(1).setDuration(100).setInterpolator(new OvershootInterpolator(2));

            }
        });
        view.animate().alpha(1).setDuration(400).setInterpolator(AnimateUtil.interpolator);
    }
    /**
     *放大到scale2>1
     * 缩小到scale1<1
     *
     * 缩小到1
     * 用时 duration
     * @param view
     */
    public static void onClickAnimate(final View view, float scale1, final float scale2, int  duration){

        view.animate().scaleX(scale1).scaleY(scale1).setDuration(duration).setInterpolator(AnimateUtil.interpolator).withEndAction(new Runnable() {
            @Override
            public void run() {
                view.animate().scaleX(scale2).scaleY(scale2).setInterpolator(AnimateUtil.interpolator).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        view.animate().scaleX(1).scaleY(1).setInterpolator(AnimateUtil.interpolator);
                    }
                });
            }
        });
    }
    /**
     *
     *
     * 用时 duration
     * @param view
     */
    public static void onClickRipple(final View view, float scale1, final int  duration){

        view.animate().scaleX(scale1).scaleY(scale1).setDuration(duration).setInterpolator(AnimateUtil.interpolator).withEndAction(new Runnable() {
            @Override
            public void run() {
                view.animate().alpha(0.2f).setInterpolator(AnimateUtil.interpolator).setDuration(duration).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        view.setScaleY(1);
                        view.setScaleX(1);
                        view.setAlpha(1);


                    }
                });
            }
        });
    }

    /**
     *
     *
     * 用时 duration
     * @param view
     */
    public static void onClickRippleWithEndAction(final View view, final int  duration, final Runnable runnable){
      final float scaleY=  view.getScaleX();
        final float scaleX= view.getScaleX();
        view.animate().alpha(0.6f).setInterpolator(AnimateUtil.interpolator).setDuration(duration);
        view.animate().scaleX(scaleX*1.3f).scaleY(scaleY*1.3f).setDuration(duration).setInterpolator(AnimateUtil.interpolator).withEndAction(new Runnable() {
            @Override
            public void run() {
                view.animate().alpha(1f).setInterpolator(AnimateUtil.interpolator).setDuration(50);
                view.animate().scaleX(scaleX).scaleY(scaleY).setDuration(50).setInterpolator(AnimateUtil.interpolator).withEndAction(runnable);
            }
        });

    }



    public static void animateViewIn(final View mView){
        Animation anim = AnimationUtils.loadAnimation(mView.getContext(),
                R.anim.push_top_in3);
        anim.setInterpolator(new FastOutSlowInInterpolator());
        anim.setDuration(250);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationStart(Animation animation) {
                mView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        mView.startAnimation(anim);
    }

    public static void animateViewOut(final View mView){
        animateViewOut(mView, 250);
    }

    public static void animateViewOut(final View mView, long duration) {
        Animation anim = AnimationUtils.loadAnimation(mView.getContext(), R.anim.push_top_out3);
        anim.setInterpolator(new FastOutSlowInInterpolator());
        anim.setFillAfter(true);
        anim.setDuration(duration);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                mView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        mView.startAnimation(anim);
    }

}
