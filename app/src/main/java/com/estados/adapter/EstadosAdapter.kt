package com.estados.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.estados.R
import com.estados.databinding.FragmentEstadoCardBinding
import com.estados.model.Estado
import com.estados.ui.EstadosFragmentDirections

class EstadosAdapter : RecyclerView.Adapter<EstadosAdapter.EstadoViewHolder>() {
    private var stateList = emptyList<Estado>()
    inner class EstadoViewHolder(private val itemBinding: FragmentEstadoCardBinding)
        : RecyclerView.ViewHolder(itemBinding.root) {
        fun render(estado: Estado) {
            itemBinding.stateName.text = estado.name
            itemBinding.stateCapital.text =  estado.capital
            itemBinding.statePopulation.text = estado.population.toString()
            itemBinding.hasCoast.text = if (estado.hasCoast == 1) "Si" else "No"
            itemBinding.cardView.setOnClickListener {
                val nav = EstadosFragmentDirections.actionEstadosFragmentToUpdateEstado(estado)
                itemView.findNavController().navigate(nav)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstadoViewHolder {
        val itemBinding = FragmentEstadoCardBinding
            .inflate(
                LayoutInflater.from(parent.context)
                ,parent
                ,false)
        return EstadoViewHolder(itemBinding)
    }

    //Con una "cajita" creada... se pasa a dibujar los datos del lugar x
    override fun onBindViewHolder(holder: EstadoViewHolder, position: Int) {
        val currentState = stateList[position]
        holder.render(currentState)
    }
    override fun getItemCount(): Int {
        return stateList.size
    }
    fun setLugares(states : List<Estado>) {
        stateList = states
        notifyDataSetChanged()
    }
}