package br.com.victoriasantos.libertadoresupdates.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.victoriasantos.libertadoresupdates.R
import br.com.victoriasantos.libertadoresupdates.domain.Match
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.jogos_acontendo_item.view.*


class JogosAcontecendoAdapter(private val dataSet: Array<Match>) : RecyclerView.Adapter<JogosAcontecendoAdapter.JogosAcontecendoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JogosAcontecendoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.jogos_acontendo_item, parent, false)
        return JogosAcontecendoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: JogosAcontecendoViewHolder, position: Int) {
        val match = dataSet[position]
        Picasso.get().load(match.logo_time_casa).into(holder.logo_tc)
        Picasso.get().load(match.logo_time_fora).into(holder.logo_tf)
        holder.nome_tc.text = match.nome_time_casa
        holder.nome_tf.text = match.nome_time_fora
        holder.placar.text = match.placar
        holder.rodada.text = match.rodada
        holder.tempo.text = match.tempo
        holder.status.text = match.status
        holder.data.text = match.data
        holder.arbitro.text = match.arbitro
        holder.estadio.text = match.estadio

        var str = ""
        match.eventos?.forEach { e ->
            str =
                str.plus("${e.evento_tempo}\n ${e.evento_acrescimo}\n ${e.evento_teamName}\n ${e.evento_player}\n ${e.evento_assist}\n ${e.evento_type}\n ${e.evento_detail}\n ${e.evento_comments}")
        }
            holder.eventos.text = str
    }



    class JogosAcontecendoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val logo_tc: ImageView = itemView.logoCasa
        val logo_tf: ImageView = itemView.logoFora
        val nome_tc: TextView = itemView.nomeCasa
        val nome_tf: TextView = itemView.nomeFora
        val placar: TextView = itemView.placar
        val tempo: TextView = itemView.tempo
        val status: TextView = itemView.status
        val data: TextView = itemView.data
        val rodada: TextView = itemView.rodada
        val eventos: TextView = itemView.eventos
        val arbitro: TextView = itemView.arbitro
        val estadio: TextView = itemView.estadio
    }

}