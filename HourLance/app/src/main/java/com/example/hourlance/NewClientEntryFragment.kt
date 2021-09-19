package com.example.hourlance

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.hourlance.databinding.FragmentNewClientEntryBinding
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.io.Writer
import kotlin.math.log

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
        val fileName = "savedClients.txt"
        val file = File(fileName)
        if (!file.exists())
            file.createNewFile()
        Log.d("WARN","ABOUT TO SAVE")
        Log.d("WARN","SAVED")
        return try {
            file.printWriter().use {
                out->
                out.println("Name/${_client.name}")
                out.println("Project/${_client.projectType.toString()}")
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
            Log.d("ERM", "File did try to write")
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        } finally {
            Log.d("_____SUCCESS", "FILE WRITTEN TO ________")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    var client = ClientClass("a","b",1.0,1.0,"c",1,"d","e","f","g")

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
}