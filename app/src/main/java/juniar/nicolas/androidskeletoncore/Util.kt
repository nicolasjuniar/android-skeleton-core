package juniar.nicolas.androidskeletoncore

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

object Util {

    fun Context.showToast(message: String, isLong: Boolean = false) {
        Toast.makeText(this, message, if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
    }

    fun ImageView.loadImage(url:String, context: Context) {
        Glide.with(context)
            .load(url)
            .centerCrop()
            .into(this)
    }
}