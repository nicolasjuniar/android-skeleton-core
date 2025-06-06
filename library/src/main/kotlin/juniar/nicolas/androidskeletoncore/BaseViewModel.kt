package juniar.nicolas.androidskeletoncore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    protected val infoMessage = MutableLiveData<String>()
    protected val isLoading = MutableLiveData<Boolean>()
    protected val isSuccess = MutableLiveData<Boolean>()

    fun observeInfoMessage() = infoMessage
    fun observeIsLoading() = isLoading
    fun observeIsSuccess() = isSuccess
}