package com.example.aimcalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var date: TextView? = null
    private var age: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn: Button = findViewById(R.id.age_btn)

        date = findViewById(R.id.selected_date)
        age = findViewById(R.id.aim)

        btn.setOnClickListener { funDatePicker() }

    }

    private fun funDatePicker() {

        val calenderInstance = Calendar.getInstance()

        val year1 = calenderInstance.get(Calendar.YEAR)
        val month1 = calenderInstance.get(Calendar.MONTH)
        val day1 = calenderInstance.get(Calendar.DAY_OF_MONTH)

        val datePickerVar = DatePickerDialog(
            this, { _, year, month, day ->

                //Toast.makeText(this, "$year, $month, $day" , Toast.LENGTH_LONG).show()
                val dateSelected = "$day/${month + 1}/$year"
                date?.text = dateSelected

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(dateSelected)
                //.time gives time in milliseconds from 1st of January,1970 till theDate, so convert it to minutes /60000
                theDate?.let{
                    val aim = theDate.time / 60000

                    val dateToday = sdf.parse(sdf.format(System.currentTimeMillis()))

                    dateToday?.let{
                        val aimToday = dateToday.time / 60000

                        val finalAIM = aimToday - aim

                        age?.text = finalAIM.toString()
                    }
                }

            },
            year1, month1, day1

        )

        datePickerVar.datePicker.maxDate =System.currentTimeMillis()

        datePickerVar.show()
    }


}