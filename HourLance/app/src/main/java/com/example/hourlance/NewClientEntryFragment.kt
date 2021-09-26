package com.example.hourlance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.hourlance.databinding.FragmentNewClientEntryBinding
import java.io.File

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.Month

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
    ): View {
        _binding = FragmentNewClientEntryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSaveClientEntry.setOnClickListener {
            if (checkInputs()) {
                Thread.sleep(100)
                val client = ClientClass(
                    binding.enterClientName.text.toString(),
                    binding.enterProjectType.text.toString(),
                    binding.enterHourlyRate.text.toString().toDouble(),
                    binding.enterOvertimeRate.text.toString().toDouble(),
                    binding.enterClientName.text.toString(),
                    binding.enterContactPhone.text.toString().toInt(),
                    binding.enterContactEmail.text.toString(),
                    binding.enterContactAddress.text.toString(),
                    binding.enterContactState.text.toString(),
                    binding.enterContactWebsite.text.toString()
                )

                if (!saveClientEntryToFile(client)) {
                        Toast.makeText(
                            context,
                            "SAVE FAILED, RE-ENTER DATA AND TRY AGAIN",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    findNavController().navigate(R.id.action_newClientEntryFragment_to_FirstFragment)
            }
            else
                Toast.makeText(
                    context,
                    "PLEASE ENTER VALID DATA",
                    Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveClientEntryToFile(_client: ClientClass): Boolean {
        val path: File? = context?.filesDir

        val fileName = "savedClients.txt"
        val file = File(path, fileName)
        if (!file.exists())
            file.createNewFile()
        return try {
            file.printWriter().use {
                out->
                out.println("Name/${_client.name}")
                out.println("Project/${_client.projectType}")
                out.println("Rate/${_client.rate}")
                out.println("OTRate/${_client.overtimeRate}")
                out.println("contactName/${_client.contactName}")
                out.println("contactPhone/${_client.contactPhone}")
                out.println("contactEmail/${_client.contactEmail}")
                out.println("contactAddress/${_client.contactAddress}")
                out.println("contactState/${_client.contactState}")
                out.println("contactWebsite/${_client.contactWebsite}")
                out.println("&&End of Client")
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    private fun checkInputs(): Boolean {
        if ( binding.enterClientName.text.toString() == "Name" ||
             binding.enterClientName.text.toString() == "" ||
             binding.enterProjectType.text.toString() == "Type" ||
             binding.enterProjectType.text.toString() == "" ||
             binding.enterHourlyRate.text.toString() == "Rate/hour" ||
             binding.enterHourlyRate.text.toString() == "" ||
             binding.enterOvertimeRate.text.toString() == "Overtime rate" ||
             binding.enterOvertimeRate.text.toString() == "" ||
             binding.enterClientName.text.toString() == "Client" ||
             binding.enterContactName.text.toString() == "" ||
             binding.enterContactPhone.text.toString() == "Phone" ||
             binding.enterContactPhone.text.toString() == "" ||
             binding.enterContactEmail.text.toString() == "Email" ||
             binding.enterContactEmail.text.toString() == "" ||
             binding.enterContactAddress.text.toString() == "Address" ||
             binding.enterContactAddress.text.toString() == "" ||
             binding.enterContactState.text.toString() == "State" ||
             binding.enterContactState.text.toString() == "" ||
             binding.enterContactWebsite.text.toString() == "Website" ||
             binding.enterContactWebsite.text.toString() == ""
        )
            return false
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}