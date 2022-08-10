package com.vogella.android.pistis

import android.content.Intent
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.main_page.*
import kotlinx.android.synthetic.main.wrong_answer_popup.*
import java.lang.Math.abs
import java.sql.Time
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread
import kotlin.concurrent.timer

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)

        val answer = intent.getStringExtra("ans")
        val answer_unit = answer.toString().uppercase().split("")
        val target_time_origin = intent.getStringExtra("target_time")
        val target_time = target_time_origin.toString().split(":")
        val target_hour = target_time[0].toInt()
        val target_minute = target_time[1].toInt()

        val format = SimpleDateFormat("HH,mm,ss")
        var current_time : Long

        var timechecker = timer(period = 1000){
            current_time = System.currentTimeMillis()
            var time_slice = format.format(current_time).split(",")
            var left_hour : Int = target_hour - time_slice[0].toInt() -1
            var left_minute : Int = target_minute - time_slice[1].toInt()
            if(left_minute < 0){ left_minute += 60 }
            else{ left_hour += 1 }
            var left_second = 60 - time_slice[2].toInt()
            var left_time = StringBuilder(left_hour.toString()).append(" : ").append(left_minute.toString()).append(" : ").append(left_second.toString())
            Lefttime.setText(left_time)
        }

        submit.setOnClickListener{
            if(answer.toString().uppercase() == Answerinput.text.toString().uppercase()){
                timechecker.cancel()
                Lefttime.setTextColor(getColor(R.color.white))

                answer1.setText(answer_unit[0])
                answer1.setTextColor(getColor(R.color.white))
                answer2.setText(answer_unit[1])
                answer2.setTextColor(getColor(R.color.white))
                answer3.setText(answer_unit[2])
                answer3.setTextColor(getColor(R.color.white))
                answer4.setText(answer_unit[3])
                answer4.setTextColor(getColor(R.color.white))
                answer5.setText(answer_unit[4])
                answer5.setTextColor(getColor(R.color.white))
                answer6.setText(answer_unit[5])
                answer6.setTextColor(getColor(R.color.white))
                answer7.setText(answer_unit[6])
                answer7.setTextColor(getColor(R.color.white))
                answer8.setText(answer_unit[7])
                answer8.setTextColor(getColor(R.color.white))

                submit.setVisibility(View.INVISIBLE)

                Answerinput.setBackgroundColor(View.INVISIBLE)
                Answerinput.setText("Congratulation!!")
                Answerinput.setTextSize(TypedValue.COMPLEX_UNIT_PX,80.toFloat())

            }
            else{
                val wrong_answer = Intent(this,WrongAnswer::class.java)
                wrong_answer.putExtra("ans",answer)
                wrong_answer.putExtra("target_time",target_time_origin)
                timechecker.cancel()
                startActivity(wrong_answer)
            }
        }

    }
}