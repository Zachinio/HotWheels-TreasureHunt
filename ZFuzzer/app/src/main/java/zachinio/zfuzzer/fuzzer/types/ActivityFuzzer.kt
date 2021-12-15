package zachinio.zfuzzer.fuzzer.types

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageInfo
import android.net.Uri
import zachinio.zfuzzer.App
import zachinio.zfuzzer.fuzzer.FuzzerInterface

class ActivityFuzzer : FuzzerInterface {

    override fun getType(): String {
        return "activity"
    }

    override fun fuzz(packageInfo: PackageInfo): HashMap<ComponentName, Boolean> {
        val results = hashMapOf<ComponentName, Boolean>()
        return results
    }
}