package zachinio.treasurehunt.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import org.json.JSONArray
import org.json.JSONObject
import zachinio.treasurehunt.databinding.FragmentHomeBinding
import kotlin.random.Random

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.carsJson.observe(viewLifecycleOwner, Observer {
            onCarsReady(JSONArray(it))
        })
        return root
    }

    private fun onCarsReady(carsJsonString: JSONArray) {
        var index = Random.nextInt(carsJsonString.length())
        if (index >= carsJsonString.length()) {
            index = Random.nextInt(carsJsonString.length())
        }
        val car: JSONObject = carsJsonString.get(index) as JSONObject

        binding.carTv.text = car.getString("name")
        Picasso.get().load(car.getString("img")).fit().centerInside().into(binding.carIv)

        binding.treasureHuntBt.setOnClickListener {
            var res = "you'r right!"
            if (!car.getString("isTreasure").toBoolean()) {
                res = "Failed! You bought a mainline!!!"
            }
            Toast.makeText(activity, res, Toast.LENGTH_LONG).show()
            Thread.sleep(300)
            onCarsReady(carsJsonString)
        }

        binding.mainBt.setOnClickListener {
            var res = "you'r right!"
            if (car.getString("isTreasure").toBoolean()) {
                res = "Failed! You missed a treasure hunt!!!"
            }

            Toast.makeText(activity, res, Toast.LENGTH_LONG).show()
            Thread.sleep(300)
            onCarsReady(carsJsonString)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}