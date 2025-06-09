package juniar.nicolas.androidskeletoncore.util

import android.view.View

object ViewUtil {
    fun View.gone() {
        this.visibility = View.GONE
    }

    fun View.visible() {
        this.visibility = View.VISIBLE
        this.isEnabled = true
    }
}