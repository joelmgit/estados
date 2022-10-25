package com.estados.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.estados.R
import com.estados.databinding.FragmentAddEstadoBinding
import com.estados.model.Estado
import com.estados.viewmodel.EstadoViewModel


class AddEstadoFragment : Fragment() {

    private var _binding: FragmentAddEstadoBinding? = null
    private val binding get() = _binding!!
    private lateinit var stateViewModel: EstadoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        stateViewModel = ViewModelProvider(this).get(EstadoViewModel::class.java)
        _binding = FragmentAddEstadoBinding.inflate(inflater, container, false)

        binding.addStateButton.setOnClickListener({addState()})


        return binding.root

    }

    private fun addState() {
        val name = binding.addStateName.text.toString()
        val capital = binding.addStateCapital.text.toString()
        val population = binding.addStatePopulation.text.toString().toInt()
        val hasCoast = if (binding.addHasCoast.isChecked) 1 else 0
        if(name.isNotEmpty()){
            val state = Estado(0, name, capital, population, hasCoast)
            stateViewModel.saveState(state);
            Toast.makeText(requireContext(),getString(R.string.add_success), Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_AddEstadoFragment_to_EstadosFragment)
        }else{
            Toast.makeText(requireContext(),getString(R.string.add_error), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}