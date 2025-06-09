package juniar.nicolas.androidskeletoncore.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.viewbinding.ViewBinding

abstract class BaseViewBindingActivity<VB : ViewBinding> : AppCompatActivity() {

    protected val viewBinding: VB by lazy {
        getContentView()
    }

    abstract fun getContentView(): VB

    abstract fun onViewReady(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        onViewReady(savedInstanceState)
    }

    protected fun createActivityResultLauncer(
        onActivityResult: (activityResult: ActivityResult) -> Unit,
    ) = this.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        onActivityResult.invoke(it)
    }

    protected fun <T> LiveData<T>.onChangeValue(action: (T) -> Unit) {
        observe(this@BaseViewBindingActivity) { data -> data?.let(action) }
    }

    protected inline fun <reified T : Activity> ActivityResultLauncher<Intent>.launchIntent(
        bundle: Bundle? = null,
        isFinishAffinity: Boolean = false,
        isFinish: Boolean = false,
        isClearTopSingleTop: Boolean = false
    ) {
        val intent = Intent(this@BaseViewBindingActivity, T::class.java)
        if (isClearTopSingleTop) {
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        bundle?.let {
            intent.putExtras(bundle)
        }
        this.launch(intent)
        if (isFinish) {
            finish()
        }
        if (isFinishAffinity) {
            finishAffinity()
        }
    }
}