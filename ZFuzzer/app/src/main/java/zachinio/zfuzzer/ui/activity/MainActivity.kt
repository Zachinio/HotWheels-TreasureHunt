package zachinio.zfuzzer.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import zachinio.zfuzzer.R
import zachinio.zfuzzer.fuzzer.ZFuzzer

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        findViewById<View>(R.id.view).setOnClickListener {
            GlobalScope.launch {
                ZFuzzer(packageManager).fuzzAll()
            }
        }
    }
}