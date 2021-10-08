package zachinio.treasurehunt.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import zachinio.treasurehunt.App
import kotlin.concurrent.thread

class HomeViewModel : ViewModel() {

    private var _text = MutableLiveData<String>()


    init {
        Thread {
            _text.postValue(
                String(App.instance.applicationContext.assets.open("cars.json").readBytes())
            )
        }.start()
    }

    val carsJson: LiveData<String> = _text
}