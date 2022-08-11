package com.vogella.android.pistis

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.main_page.*
import kotlinx.android.synthetic.main.wrong_answer_popup.*
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
        val victory_background = MediaPlayer.create(this,R.raw.victory_background)
        val victory = MediaPlayer.create(this,R.raw.victory)
        val ending_music = MediaPlayer.create(this, R.raw.imfree)
        var time = 0

        val format = SimpleDateFormat("HH,mm,ss")
        var current_time : Long

        val background_music = MediaPlayer.create(this,R.raw.darkworld)

        var music_player = timer(period = 1510){
            background_music.start()
        }


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

            if(left_hour == 0 && left_minute <= 20){
                change_view(answer1,answer_unit[1])
            }
            if(left_hour == 0 && left_minute <= 16){
                change_view(answer2,answer_unit[2])
            }
            if(left_hour == 0 && left_minute <= 12){
                change_view(answer3,answer_unit[3])
            }
            if(left_hour == 0 && left_minute <= 8){
                change_view(answer4,answer_unit[4])
            }
            if(left_hour == 0 && left_minute <= 6){
                change_view(answer5,answer_unit[5])
            }
            if(left_hour == 0 && left_minute <= 4){
                change_view(answer6,answer_unit[6])
            }
            if(left_hour == 0 && left_minute <= 2){
                change_view(answer7,answer_unit[7])
            }
            if(left_hour == 0 && left_minute == 0){
                change_view(answer8,answer_unit[8])
            }
        }

        submit.setOnClickListener{
            if(answer.toString().uppercase() == Answerinput.text.toString().uppercase()){
                victory_background.start()
                timechecker.cancel()
                Lefttime.setTextColor(getColor(R.color.white))

                answer1.setText(answer_unit[1])
                answer1.setTextColor(getColor(R.color.white))
                answer1.setBackgroundResource(0)

                answer2.setText(answer_unit[2])
                answer2.setTextColor(getColor(R.color.white))
                answer2.setBackgroundResource(0)

                answer3.setText(answer_unit[3])
                answer3.setTextColor(getColor(R.color.white))
                answer3.setBackgroundResource(0)

                answer4.setText(answer_unit[4])
                answer4.setTextColor(getColor(R.color.white))
                answer4.setBackgroundResource(0)

                answer5.setText(answer_unit[5])
                answer5.setTextColor(getColor(R.color.white))
                answer5.setBackgroundResource(0)

                answer6.setText(answer_unit[6])
                answer6.setTextColor(getColor(R.color.white))
                answer6.setBackgroundResource(0)

                answer7.setText(answer_unit[7])
                answer7.setTextColor(getColor(R.color.white))
                answer7.setBackgroundResource(0)

                answer8.setText(answer_unit[8])
                answer8.setTextColor(getColor(R.color.white))
                answer8.setBackgroundResource(0)

                submit.setVisibility(View.INVISIBLE)

                Answerinput.setBackgroundColor(View.INVISIBLE)
                Answerinput.setText("Congratulation!!")
                Answerinput.setTextSize(TypedValue.COMPLEX_UNIT_PX,80.toFloat())
                divider3.setVisibility(View.VISIBLE)

                var waiter = timer(period = 1000){
                    time++
                    if(time == 5){
                        victory.start()
                    }
                    if(time == 6)
                        ending_music.start()
                }
            }
            else{
                val wrong_answer = Intent(this,WrongAnswer::class.java)
                wrong_answer.putExtra("ans",answer)
                wrong_answer.putExtra("target_time",target_time_origin)
                startActivity(wrong_answer)
                Answerinput.text = null
            }
        }

    }

    fun change_view(view: TextView, s:String){
        val handler :Handler = object : Handler(Looper.getMainLooper()){
            override fun handleMessage(msg: Message) {
                view.setText(s)
                view.setTextColor(getColor(R.color.red))
                view.setBackgroundResource(0)
            }
        }
        handler.obtainMessage().sendToTarget()
    }
}