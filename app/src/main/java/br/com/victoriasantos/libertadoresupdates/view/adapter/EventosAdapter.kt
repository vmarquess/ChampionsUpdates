package br.com.victoriasantos.libertadoresupdates.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.victoriasantos.libertadoresupdates.R
import br.com.victoriasantos.libertadoresupdates.domain.Evento
import kotlinx.android.synthetic.main.eventos_item.view.*



class EventosAdapter(private val dataSet: Array<Evento>?) : RecyclerView.Adapter<EventosAdapter.EventosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.eventos_item, parent, false)
        return EventosViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (dataSet != null) {
            return dataSet.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: EventosViewHolder, position: Int) {
        val evento = dataSet?.get(position)
        holder.tempo.text = evento?.evento_tempo
        holder.acrescimo.text = evento?.evento_acrescimo
        holder.nome_time.text = evento?.evento_teamName
        holder.jogador.text = evento?.evento_player
        holder.assintencia.text = evento?.evento_assist
        holder.tipo.text = evento?.evento_type
        holder.detalhes.text = evento?.evento_detail
        holder.comentarios.text = evento?.evento_comments
    }

    class EventosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tempo: TextView = itemView.tempo
        val acrescimo: TextView = itemView.acrescimo
        val nome_time: TextView = itemView.nome_time
        val jogador: TextView = itemView.jogador
        val assintencia: TextView = itemView.assistencia
        val tipo: TextView = itemView.tipo_evento
        val detalhes: TextView = itemView.detalhes
        val comentarios: TextView = itemView.comentarios

    }
}