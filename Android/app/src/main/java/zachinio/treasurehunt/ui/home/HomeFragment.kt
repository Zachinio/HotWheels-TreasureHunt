package zachinio.treasurehunt.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import org.json.JSONArray
import org.json.JSONObject
import zachinio.treasurehunt.R
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
        Glide.with(this).load(car.getString("img")).centerInside().into(binding.carIv)

        binding.treasureHuntBt.setOnClickListener {
            var res = "you'r right!"
            var color = ContextCompat.getColor(it.context, R.color.white)
            if (!car.getString("isTreasure").toBoolean()) {
                res = "Failed! You bought a mainline!!!"
                color = ContextCompat.getColor(it.context, R.color.red)

            }
            binding.background.setBackgroundColor(color)
            Thread.sleep(300)
            onCarsReady(carsJsonString)
        }

        binding.mainBt.setOnClickListener {
            var res = "you'r right!"
            var color = ContextCompat.getColor(it.context, R.color.white)
            if (car.getString("isTreasure").toBoolean()) {
                color = ContextCompat.getColor(it.context, R.color.red)
                res = "Failed! You missed a treasure hunt!!!"
            }
            binding.background.setBackgroundColor(color)

            Thread.sleep(300)
            onCarsReady(carsJsonString)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}