package zachinio.zfuzzer.utils

import android.util.Log

object Logger {

    const val TAG = "ZFuzzer"

    fun log(log: String?) {
        log?.let {
            Log.d(TAG, it)
        }
    }
}