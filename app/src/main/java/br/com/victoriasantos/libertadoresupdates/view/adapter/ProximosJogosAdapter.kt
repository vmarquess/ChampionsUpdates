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
import kotlinx.android.synthetic.main.nextmatch_item.view.*
import kotlinx.android.synthetic.main.nextmatch_item.view.arbitro
import kotlinx.android.synthetic.main.nextmatch_item.view.data
import kotlinx.android.synthetic.main.nextmatch_item.view.estadio
import kotlinx.android.synthetic.main.nextmatch_item.view.logoCasa
import kotlinx.android.synthetic.main.nextmatch_item.view.logoFora
import kotlinx.android.synthetic.main.nextmatch_item.view.nomeCasa
import kotlinx.android.synthetic.main.nextmatch_item.view.nomeFora
import kotlinx.android.synthetic.main.nextmatch_item.view.rodada
import org.w3c.dom.Text

class ProximosJogosAdapter(private val dataSet: Array<Match>) : RecyclerView.Adapter<ProximosJogosAdapter.ProximosJogosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProximosJogosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.nextmatch_item, parent, false)
        return ProximosJogosViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ProximosJogosViewHolder, position: Int) {
        val match = dataSet[position]
        Picasso.get().load(match.logo_time_casa).into(holder.logo_tc)
        Picasso.get().load(match.logo_time_fora).into(holder.logo_tf)
        holder.nome_tc.text = match.nome_time_casa
        holder.nome_tf.text = match.nome_time_fora
        holder.rodada.text = match.rodada
        holder.data.text = match.data
        holder.arbitro.text = match.arbitro
        holder.estadio.text = match.estadio
    }

    class ProximosJogosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val logo_tc: ImageView = itemView.logoCasa
        val logo_tf: ImageView = itemView.logoFora
        val nome_tc: TextView = itemView.nomeCasa
        val nome_tf: TextView = itemView.nomeFora
        val data: TextView = itemView.data
        val rodada: TextView = itemView.rodada
        val arbitro: TextView = itemView.arbitro
        val estadio: TextView = itemView.estadio
    }
}