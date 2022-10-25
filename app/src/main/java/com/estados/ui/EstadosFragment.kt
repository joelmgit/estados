package com.estados.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.estados.R
import com.estados.adapter.EstadosAdapter
import com.estados.databinding.FragmentEstadosBinding
import com.estados.viewmodel.EstadoViewModel


class EstadosFragment : Fragment() {

    private var _binding: FragmentEstadosBinding? = null
    private val binding get() = _binding!!
    private lateinit var stateViewModel: EstadoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        stateViewModel = ViewModelProvider(this).get(EstadoViewModel::class.java)
        _binding = FragmentEstadosBinding.inflate(inflater, container, false)

        val stateAdapter = EstadosAdapter()
        val recycler = binding.recycler
        recycler.adapter = stateAdapter
        recycler.layoutManager = LinearLayoutManager(requireContext())

        stateViewModel.getStates.observe(viewLifecycleOwner){
                states -> stateAdapter.setLugares(states)
        }

      return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}