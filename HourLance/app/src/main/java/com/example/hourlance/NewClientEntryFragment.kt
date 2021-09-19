package com.example.hourlance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hourlance.databinding.FragmentNewClientEntryBinding
import com.example.hourlance.databinding.FragmentNewTimeEntryBinding

/**
 * A simple [Fragment] subclass.
 * Use the [NewClientEntryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewClientEntryFragment : Fragment() {
    private var _binding: FragmentNewClientEntryBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewClientEntryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSaveClientEntry.setOnClickListener {
            findNavController().navigate(R.id.action_newClientEntryFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}