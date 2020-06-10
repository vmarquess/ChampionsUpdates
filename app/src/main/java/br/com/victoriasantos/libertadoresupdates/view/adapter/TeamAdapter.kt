package br.com.victoriasantos.libertadoresupdates.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.victoriasantos.libertadoresupdates.R
import br.com.victoriasantos.libertadoresupdates.domain.Team
import br.com.victoriasantos.libertadoresupdates.view.activities.TeamsActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.time_item.view.*


class TeamAdapter(private val activity: TeamsActivity, private val dataSet: Array<Team>) :
    RecyclerView.Adapter<TeamAdapter.TimeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.time_item, parent, false)
        return TimeViewHolder(view)

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: TimeViewHolder, position: Int) {
        val time = dataSet[position]
        Picasso.get().load(time.logo).into(holder.escudo)
        holder.escudo.setOnClickListener {
            activity.players(time.id!!.toString(), time.name!!)
        }
        holder.nome.text = time.name
        holder.pais.text = time.country
        holder.estadio.text = time.estadio
        holder.fundacao.text = time.fundacao
    }

    class TimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val escudo: ImageView = itemView.escudo
        val nome: TextView = itemView.rank
        val pais: TextView = itemView.empates
        val estadio: TextView = itemView.estadio
        val fundacao: TextView = itemView.fundacao


    }

}


