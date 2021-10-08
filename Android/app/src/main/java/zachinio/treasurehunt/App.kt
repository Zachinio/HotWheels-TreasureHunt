package zachinio.treasurehunt

import android.app.Application

class App : Application() {

    companion object {
        lateinit var instance: Application
    }

    init {
        instance = this
    }
}