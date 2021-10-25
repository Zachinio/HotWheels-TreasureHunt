package zachinio.treasurehunt.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import zachinio.treasurehunt.R

class TreasuresAdapter : RecyclerView.Adapter<TreasuresAdapter.TreasuresViewHolder>() {

    val cars = arrayListOf<Car>()

    class TreasuresViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val carNameTv = itemView.findViewById<TextView>(R.id.carNameTv)
        val carSeriesTv = itemView.findViewById<TextView>(R.id.carSeriesTv)
        val carYearTv = itemView.findViewById<TextView>(R.id.carYearTv)
        val carIv = itemView.findViewById<ImageView>(R.id.carTreasureIv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreasuresViewHolder {
        return TreasuresViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_treasure, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TreasuresViewHolder, position: Int) {
        val car = cars[position]
        holder.carNameTv.text = car.name
        holder.carSeriesTv.text = car.series
        holder.carYearTv.text = car.year
        Glide.with(holder.carIv).load(car.image).into(holder.carIv)

    }

    override fun getItemCount(): Int {
        return cars.size
    }
}