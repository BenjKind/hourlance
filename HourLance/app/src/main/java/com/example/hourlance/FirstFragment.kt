package com.example.hourlance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hourlance.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainNewButton.setOnClickListener {

            if (binding.newTimeEntryButton.visibility == View.INVISIBLE) {
                binding.newTimeEntryButton.visibility = View.VISIBLE
                binding.newClientEntryButton.visibility = View.VISIBLE
            } else {
                binding.newTimeEntryButton.visibility = View.INVISIBLE
                binding.newClientEntryButton.visibility = View.INVISIBLE
            }
        }
        binding.FirstFragmentWhiteSpace.setOnClickListener {
            binding.newTimeEntryButton.visibility = View.INVISIBLE
            binding.newClientEntryButton.visibility = View.INVISIBLE
        }

        binding.newClientEntryButton.setOnClickListener {
            Thread.sleep(100)
            findNavController().navigate(R.id.action_FirstFragment_to_newClientEntryFragment)
        }

        binding.newTimeEntryButton.setOnClickListener {
            Thread.sleep(100)
            findNavController().navigate(R.id.action_FirstFragment_to_newTimeEntryFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}