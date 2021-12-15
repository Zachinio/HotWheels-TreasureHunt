package zachinio.zfuzzer.fuzzer

import android.content.ComponentName
import android.content.pm.PackageInfo

interface FuzzerInterface {

    fun getType(): String

    fun fuzz(packageInfo: PackageInfo): HashMap<ComponentName, Boolean>
}