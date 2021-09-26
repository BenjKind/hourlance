package com.example.hourlance

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hourlance.databinding.FragmentNewTimeEntryBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import com.example.hourlance.MainActivity


class NewTimeEntryFragment : Fragment() {
    private var _binding: FragmentNewTimeEntryBinding? = null
    private val binding get() = _binding!!

    // USING https://www.tutorialkart.com/kotlin-android/android-datepicker-kotlin-example/
    lateinit var button_date: Button//? = null
    var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        button_date = binding.buttonSelectDate
        button_date!!.text = "--/--/----"
        // UPDATE BUTTON TO DATE AFTER SETTING
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, day)
                updateDateInView()
            }
        }

        button_date.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                DatePickerDialog(v.context,
                    dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        })
    }

    private fun updateDateInView() {
        val myFormat = "MM/dd/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        button_date!!.text = sdf.format(cal.time)
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
                    cal,
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

        /*binding.buttonSelectDate.setOnClickListener {
            if(binding.datePicker.visibility == View.INVISIBLE) {
                binding.datePicker.visibility = View.VISIBLE
            } else {
                binding.datePicker.visibility = View.INVISIBLE
            }
        }*/
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
        if (
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