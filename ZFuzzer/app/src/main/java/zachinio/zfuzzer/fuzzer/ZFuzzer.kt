package zachinio.zfuzzer.fuzzer

import android.content.ComponentName
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import kotlinx.coroutines.*
import zachinio.zfuzzer.fuzzer.types.ActivityFuzzer
import zachinio.zfuzzer.utils.Logger

class ZFuzzer(packageManager: PackageManager) {

    private val packagesInfo: List<PackageInfo> = PackagesProvider().getPackagesInfo(packageManager)
    private val fuzzers = initFuzzers()

    suspend fun fuzzAll(): HashMap<String, HashMap<String, HashMap<ComponentName, Boolean>>> {
        Logger.log("fuzzing all")
        val results = HashMap<String, HashMap<String, HashMap<ComponentName, Boolean>>>()
        val tasks = mutableListOf<Job>()
        packagesInfo.forEach {
            tasks.add(GlobalScope.launch(Dispatchers.IO) {
                results[it.packageName] = fuzzPackage(it)
            })
        }
        tasks.joinAll()

        Logger.log("finished fuzzing all")
        return results
    }

    fun fuzzPackage(packageName: String) {
        throw Throwable("Method is not implemented yet")
    }

    private fun fuzzPackage(packageInfo: PackageInfo): HashMap<String, HashMap<ComponentName, Boolean>> {
        Logger.log("fuzzing ${packageInfo.packageName}")
        val results = HashMap<String, HashMap<ComponentName, Boolean>>()
        fuzzers.forEach {
            Logger.log("fuzzing ${packageInfo.packageName} with ${it.javaClass}")
            results[it.getType()] = it.fuzz(packageInfo)
        }
        return results
    }

    private fun initFuzzers(): List<FuzzerInterface> {
        val fuzzers = arrayListOf<FuzzerInterface>()
        fuzzers.add(ActivityFuzzer())
        return fuzzers
    }

}