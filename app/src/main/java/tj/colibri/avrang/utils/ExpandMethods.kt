package tj.colibri.avrang.utils

import android.animation.Animator
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.DecelerateInterpolator


class ExpandMethods {

    fun expand(v: View, duration: Int,arrow : View) {
        val expand = v.getVisibility() !== View.VISIBLE
        val prevHeight: Int = v.getHeight()
        var height = 0
        if (expand) {
            val measureSpecParams: Int = View.MeasureSpec.getSize(View.MeasureSpec.UNSPECIFIED)
            v.measure(measureSpecParams, measureSpecParams)
            height = v.getMeasuredHeight()
            arrow.rotation = 0f
        }else{
            arrow.rotation = 180f
        }
        val valueAnimator = ValueAnimator.ofInt(prevHeight, height)
        valueAnimator.addUpdateListener { animation ->
            v.getLayoutParams().height = animation.animatedValue as Int
            v.requestLayout()
        }
        valueAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {

                if (expand) {
                    v.setVisibility(View.VISIBLE)

                }
            }

            override fun onAnimationEnd(animation: Animator?) {

                if (!expand) {
                    v.setVisibility(View.INVISIBLE)

                }
            }

            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationRepeat(animation: Animator?) {}
        })
        valueAnimator.interpolator = DecelerateInterpolator()
        valueAnimator.duration = duration.toLong()
        valueAnimator.start()
    }
}