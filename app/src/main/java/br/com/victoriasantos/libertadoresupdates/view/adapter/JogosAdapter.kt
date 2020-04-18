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
import kotlinx.android.synthetic.main.activity_jogos.*
import kotlinx.android.synthetic.main.match_item.view.*
import org.w3c.dom.Text

class JogosAdapter(private val dataSet: Array<Match>) : RecyclerView.Adapter<JogosAdapter.JogosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JogosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.match_item, parent, false)
        return JogosViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: JogosViewHolder, position: Int) {
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
    }

    class JogosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val logo_tc: ImageView = itemView.logoCasa
        val logo_tf: ImageView = itemView.logoFora
        val nome_tc: TextView = itemView.nomeCasa
        val nome_tf: TextView = itemView.nomeFora
        val placar: TextView = itemView.placar
        val tempo: TextView = itemView.tempo
        val status: TextView = itemView.status
        val data: TextView = itemView.data
        val rodada: TextView = itemView.rodada
        val arbitro: TextView = itemView.arbitro
        val estadio: TextView = itemView.estadio
    }
}