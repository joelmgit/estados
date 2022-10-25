package com.estados.ui

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.estados.R
import com.estados.adapter.EstadosAdapter
import com.estados.databinding.FragmentEstadosBinding
import com.estados.databinding.FragmentUpdateEstadoBinding
import com.estados.model.Estado
import com.estados.viewmodel.EstadoViewModel

class UpdateEstado : Fragment() {

    private val args by navArgs<UpdateEstadoArgs>()

    private var _binding: FragmentUpdateEstadoBinding? = null
    private val binding get() = _binding!!
    private lateinit var stateViewModel: EstadoViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        stateViewModel = ViewModelProvider(this).get(EstadoViewModel::class.java)
        _binding = FragmentUpdateEstadoBinding.inflate(inflater, container, false)

        binding.updateStateName.setText(args.estado.name)
        binding.updateStateCapital.setText(args.estado.capital)
        binding.updateStatePopulation.setText(args.estado.population.toString())
        binding.updateHasCoast.isChecked = args.estado.hasCoast == 1

        binding.updateStateButton.setOnClickListener { updateState() }
        binding.deleteStateButton.setOnClickListener { deleteState() }

        return binding.root
    }

    private fun deleteState() {
        val alert = AlertDialog.Builder(requireContext())
        alert.setTitle(R.string.msg_delete_state)
        alert.setMessage(getString(R.string.msg_ask_to_delete) + "${args.estado.name}?")
        alert.setPositiveButton(getString(R.string.msg_si)){_,_ ->
            stateViewModel.deleteState(args.estado)
            Toast.makeText(requireContext(),getString(R.string.msg_deleted_state),Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateEstado_to_EstadosFragment)
        }
        alert.setNegativeButton(getString(R.string.msg_no)){_,_ ->}
        alert.create().show()
    }

    private fun updateState() {
        val name = binding.updateStateName.text.toString()
        val capital = binding.updateStateCapital.text.toString()
        val population = binding.updateStatePopulation.text.toString().toInt()
        val hasCoast = if (binding.updateHasCoast.isChecked) 1 else 0
        if(name.isNotEmpty()){
            val state = Estado(args.estado.id, name, capital, population, hasCoast)
            stateViewModel.saveState(state);
            Toast.makeText(requireContext(),getString(R.string.update_success), Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateEstado_to_EstadosFragment)
        }else{
            Toast.makeText(requireContext(),getString(R.string.update_error), Toast.LENGTH_SHORT).show()
        }
    }


}