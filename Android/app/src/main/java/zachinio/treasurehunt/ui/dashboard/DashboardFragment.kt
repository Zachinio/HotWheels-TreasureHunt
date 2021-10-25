package zachinio.treasurehunt.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.json.JSONArray
import org.json.JSONObject
import zachinio.treasurehunt.R
import zachinio.treasurehunt.databinding.FragmentDashboardBinding
import zachinio.treasurehunt.databinding.FragmentHomeBinding
import zachinio.treasurehunt.ui.home.HomeViewModel
import kotlin.random.Random

class DashboardFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentDashboardBinding? = null
    private val adapter = TreasuresAdapter()

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

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.carsJson.observe(viewLifecycleOwner, Observer {
            onCarsReady(JSONArray(it))
        })

        val recyclerView = root.findViewById<RecyclerView>(R.id.treasureRc)
        recyclerView.adapter = adapter
        return root
    }

    private fun onCarsReady(carsJsonString: JSONArray) {
        val cars = arrayListOf<Car>()
        for (i in 0 until carsJsonString.length()) {
            val carJson = carsJsonString.get(i) as JSONObject
            if (!carJson.getBoolean("isTreasure")) {
                continue
            }
            val car = Car(
                if (carJson.has("name")) carJson.getString("name") else "",
                if (carJson.has("series")) carJson.getString("series") else "",
                if (carJson.has("img")) carJson.getString("img") else "",
                "2020 - 2021",
            )
            cars.add(car)
        }
        adapter.cars.clear()
        adapter.cars.addAll(cars)
        adapter.notifyDataSetChanged()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}