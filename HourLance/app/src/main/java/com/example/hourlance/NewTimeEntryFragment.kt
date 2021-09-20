package com.example.hourlance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.hourlance.databinding.FragmentNewTimeEntryBinding
import java.io.File

class NewTimeEntryFragment : Fragment() {
    private var _binding: FragmentNewTimeEntryBinding? = null
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

        _binding = FragmentNewTimeEntryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSaveTimeEntry.setOnClickListener {
            if(checkInputs()) {
                Thread.sleep(100)
                val timeEntry = TimeEntryClass(
                    binding.enterTimeDate.text.toString().toInt(),
                    binding.enterTimeTime.text.toString().toInt(),
                    binding.enterClient.text.toString()
                )
                if (!saveTimeEntryToFile(timeEntry)) {
                    Toast.makeText(
                        context,
                        "SAVE FAILED, RE-ENTER DATA AND TRY AGAIN",
                        Toast.LENGTH_LONG
                    ).show()
                }
                findNavController().navigate(R.id.action_newTimeEntryFragment_to_FirstFragment)
            }
            else
                Toast.makeText(
                    context,
                    "PLEASE ENTER VALID DATA",
                    Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveTimeEntryToFile(_timeEntry: TimeEntryClass): Boolean {
        val path: File? = context?.filesDir

        val fileName = "savedTimeEntries.txt"
        val file = File(path, fileName)
        if (!file.exists())
            file.createNewFile()
        return try {
            file.printWriter().use {
                    out->
                out.println("Date/${_timeEntry.timeDate}")
                out.println("Time/${_timeEntry.timeTime}")
                out.println("Client/${_timeEntry.timeClient}")
                out.println("&&End of Time Entry")
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    private fun checkInputs(): Boolean {
        if ( binding.enterTimeDate.text.toString() == "Date" ||
            binding.enterTimeDate.text.toString() == "" ||
            binding.enterTimeTime.text.toString() == "Time" ||
            binding.enterTimeTime.text.toString() == "" ||
            binding.enterClient.text.toString() == "Client" ||
            binding.enterClient.text.toString() == ""
        )
            return false
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}