package zachinio.zfuzzer.fuzzer

import android.content.pm.PackageInfo
import android.content.pm.PackageManager

class PackagesProvider {

    fun getPackagesInfo(packageManager: PackageManager): List<PackageInfo> {
        return packageManager.getInstalledPackages(0).map {
            return@map packageManager.getPackageInfo(
                it.packageName,
                PackageManager.GET_ACTIVITIES
                        or PackageManager.GET_SERVICES
                        or PackageManager.GET_RECEIVERS
                        or PackageManager.GET_PROVIDERS
                        or PackageManager.GET_PERMISSIONS
            )
        }
    }
}