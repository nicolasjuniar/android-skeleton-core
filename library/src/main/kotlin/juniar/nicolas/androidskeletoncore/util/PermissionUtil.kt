package juniar.nicolas.androidskeletoncore.util

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

object PermissionUtil {
    fun Context.checkBulkRequestPermissions(permissions: Array<out String>) = permissions.all {
        ActivityCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
    }

    fun String.isPermissionGranted(context: Context) =
        ActivityCompat.checkSelfPermission(context, this) == PackageManager.PERMISSION_GRANTED

    fun Activity.requestBulkPermission(permissions: Array<out String>, requestCode: Int) {
        ActivityCompat.requestPermissions(this, permissions, requestCode)
    }

    fun Activity.requestPermissionCallback(
        permission: String, requestCode: Int, onSuccess: () -> Unit
    ) {
        if (checkBulkRequestPermissions(arrayOf(permission))) {
            onSuccess.invoke()
        } else {
            requestBulkPermission(arrayOf(permission), requestCode = requestCode)
        }
    }

    fun Activity.requestBulkPermissionCallback(
        permissions: Array<out String>, requestCode: Int, onSuccess: () -> Unit
    ) {
        if (checkBulkRequestPermissions(permissions)) {
            onSuccess.invoke()
        } else {
            requestBulkPermission(permissions, requestCode = requestCode)
        }
    }

    fun IntArray.isGranted() = this.isNotEmpty() && this.first() == PackageManager.PERMISSION_GRANTED

    fun IntArray.isDenied() = this.isNotEmpty() && this.first() == PackageManager.PERMISSION_DENIED

    fun IntArray.isAllGranted(): Boolean {
        this.forEach {
            if (it == PackageManager.PERMISSION_DENIED) {
                return false
            }
        }
        return true
    }

    fun IntArray.isAllDenied(): Boolean {
        this.forEach {
            if (it == PackageManager.PERMISSION_GRANTED) {
                return true
            }
        }
        return false
    }
}