package com.srihi.jcdecaux.velostations.ui.stations.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.srihi.jcdecaux.domain.model.Station
import com.srihi.jcdecaux.velostations.R

/**
 * Station list adapter
 *
 * @constructor
 * TODO
 *
 * @param context
 */
class VeloStationListAdapter(context: Context) : RecyclerView.Adapter<StationViewHolder>() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    var onItemClickListener: OnItemClickListener? = null
    var station: List<Station> = ArrayList()

    override fun getItemCount(): Int = station.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationViewHolder {
        val holder = StationViewHolder(
            inflater.inflate(R.layout.list_item, parent, false)
        )

        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(getStation(holder.adapterPosition))
        }
        return holder
    }

    override fun onBindViewHolder(holder: StationViewHolder, position: Int) {
        holder.bind(getStation(position))
    }

    private fun getStation(position: Int): Station = station[position]

    interface OnItemClickListener {
        fun onItemClick(station: Station)
    }
}

class StationViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    private val titleTextView = itemView.findViewById<TextView>(R.id.text_view_station_title)
    private val numberTextView = itemView.findViewById<TextView>(R.id.text_view_station_number)

    fun bind(station: Station) {
        titleTextView.text = station.name
        numberTextView.text = station.number.toString()
    }
}