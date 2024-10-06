package com.example.taskreminder

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.taskreminder.databinding.ActivityAddBinding
import com.example.taskreminder.dialog.DatePickerDialog

class AddActivity : AppCompatActivity(), android.app.DatePickerDialog.OnDateSetListener {
    private val binding: ActivityAddBinding by lazy {
        ActivityAddBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val spinner = binding.spinnerRepeat
        ArrayAdapter.createFromResource(
            this,
            R.array.repeat_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        binding.btnSelectDate.setOnClickListener {
            val datePicker = DatePickerDialog()
            datePicker.show(supportFragmentManager, "datePicker")
        }

        var selectedTime = ""

        val time = binding.timePicker.setOnTimeChangedListener { _, hour, minute ->
            selectedTime = String.format("%02d:%02d", hour, minute)
        }

        binding.txtAddTask.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("SimpliRemind")
            builder.setMessage("Do you want to add this as a new task?")
            builder.setPositiveButton("Yes") { dialog, _ ->
                val intent = Intent(this, ShowActivity::class.java).apply {
                    putExtra("title", binding.etTitle.text.toString())
                    putExtra("repeat", binding.spinnerRepeat.selectedItem.toString())
                    putExtra("date", binding.btnSelectDate.text.toString())
                    putExtra("time", selectedTime)
                }
                startActivity(intent)
            }

            builder.setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }

            val dialog = builder.create()
            dialog.show()
        }

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val selectedDate = "$dayOfMonth/${month + 1}/$year"
        binding.btnSelectDate.text = selectedDate
    }
}