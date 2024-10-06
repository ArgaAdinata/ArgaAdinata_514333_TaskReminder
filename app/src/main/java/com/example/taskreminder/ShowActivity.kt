package com.example.taskreminder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.taskreminder.databinding.ActivityShowBinding

class ShowActivity : AppCompatActivity() {
    private val binding: ActivityShowBinding by lazy {
        ActivityShowBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val title = intent.getStringExtra("title")
        val repeat = intent.getStringExtra("repeat")
        val date = intent.getStringExtra("date")
        val time = intent.getStringExtra("time")

        binding.txtTitle.text = title
        binding.txtRepeat.text = repeat
        binding.txtDate.text = date
        binding.txtTime.text = time

        binding.txtAddTask.setOnClickListener {
            finish()
        }
    }
}