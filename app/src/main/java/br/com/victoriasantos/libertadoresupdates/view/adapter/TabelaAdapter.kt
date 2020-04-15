package br.com.victoriasantos.libertadoresupdates.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.victoriasantos.libertadoresupdates.R
import br.com.victoriasantos.libertadoresupdates.domain.TeamRanked
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.ranked_time_item.view.*


class TabelaAdapter (private val dataSet: Array<TeamRanked>) :
RecyclerView.Adapter<TabelaAdapter.TabelaViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TabelaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ranked_time_item, parent, false)
        return TabelaViewHolder(view)

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: TabelaViewHolder, position: Int) {
        val timeRankeado = dataSet[position]
        Picasso.get().load(timeRankeado.escudo).into(holder.escudo)
        holder.nome.text = timeRankeado.nome
        holder.rank.text = timeRankeado.rank
        holder.pontos.text = timeRankeado.pontos
        holder.grupo.text = timeRankeado.grupo
        holder.partidas.text = timeRankeado.partidas
        holder.vitorias.text = timeRankeado.vitorias
        holder.derrotas.text = timeRankeado.derrotas
        holder.empates.text = timeRankeado.empates
        holder.saldo.text = timeRankeado.saldo
    }

    class TabelaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val escudo: ImageView = itemView.escudo
        val nome: TextView = itemView.nome
        val rank: TextView = itemView.rank
        val pontos: TextView = itemView.pontos
        val grupo: TextView = itemView.grupo
        val partidas: TextView = itemView.partidas
        val vitorias: TextView = itemView.vitorias
        val derrotas: TextView = itemView.derrotas
        val empates: TextView = itemView.empates
        val saldo:TextView = itemView.saldo
    }

}