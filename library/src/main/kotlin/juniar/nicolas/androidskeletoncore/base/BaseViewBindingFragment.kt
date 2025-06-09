package juniar.nicolas.androidskeletoncore.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseViewBindingFragment<VB : ViewBinding> : Fragment() {

    private var _viewBinding: VB? = null
    protected val viewBinding get() = _viewBinding!!

    abstract fun getContentView(inflater: LayoutInflater, container: ViewGroup?): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = getContentView(inflater, container)
        return viewBinding.root
    }

    protected fun createActivityResultLauncer(
        onActivityResult: (activityResult: ActivityResult) -> Unit,
    ) = this.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        onActivityResult.invoke(it)
    }

    protected inline fun <reified T : Activity> ActivityResultLauncher<Intent>.launchIntent(
        bundle: Bundle? = null,
        isClearTopSingleTop: Boolean = false
    ) {
        val intent = Intent(requireActivity(), T::class.java)
        if (isClearTopSingleTop) {
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        bundle?.let {
            intent.putExtras(bundle)
        }
        this.launch(intent)
    }
}