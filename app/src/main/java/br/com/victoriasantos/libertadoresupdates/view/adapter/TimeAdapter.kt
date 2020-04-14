package br.com.victoriasantos.libertadoresupdates.view.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.victoriasantos.libertadoresupdates.domain.Time


class TimeAdapter(private val dataSet: Array<Time>) :
    RecyclerView.Adapter<TimeAdapter.TimeViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeViewHolder {
        //TODO

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: TimeViewHolder, position: Int) {
        //TODO

    }

    class TimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //TODO
    }

}


