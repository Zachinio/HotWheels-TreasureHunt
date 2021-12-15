package zachinio.zfuzzer

import android.app.Activity
import android.app.Application
import android.os.Bundle

class App : Application() {

    companion object {
        var currentActivity: Activity? = null
    }


    init {
        registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, p1: Bundle?) {
                currentActivity = activity
            }

            override fun onActivityStarted(activity: Activity) {
                currentActivity = activity
            }

            override fun onActivityResumed(activity: Activity) {
                currentActivity = activity
            }

            override fun onActivityPaused(p0: Activity) {

            }

            override fun onActivityStopped(p0: Activity) {
                TODO("Not yet implemented")
            }

            override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
                TODO("Not yet implemented")
            }

            override fun onActivityDestroyed(p0: Activity) {
                currentActivity = null
            }
        })
    }
}