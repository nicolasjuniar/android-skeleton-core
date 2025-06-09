package juniar.nicolas.androidskeletoncore.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

object CommonUtil {

    fun Context.showToast(message: String, isLong: Boolean = false) {
        Toast.makeText(this, message, if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
    }

    fun ImageView.loadImage(url: String, context: Context) {
        Glide.with(context)
            .load(url)
            .centerCrop()
            .into(this)
    }

    inline fun <reified T : Activity> Activity.openActivity(
        bundle: Bundle? = null,
        isFinishAffinity: Boolean = false,
        isFinish: Boolean = false,
        isClearTopSingleTop: Boolean = false
    ) {
        val intent = Intent(this, T::class.java)
        if (isClearTopSingleTop) {
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
        if (isFinish) {
            finish()
        }
        if (isFinishAffinity) {
            finishAffinity()
        }
    }
}