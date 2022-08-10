package com.vogella.android.pistis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.concurrent.TimeUnit
import kotlin.concurrent.timer

class WrongAnswer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wrong_answer_popup)

        val answer = intent.getStringExtra("ans")
        val target_time = intent.getStringExtra("target_time")
        val goback = Intent(this,GameActivity::class.java)
        var time : Int = 0
        goback.putExtra("ans",answer)
        goback.putExtra("target_time",target_time)

        var waiter = timer(period = 1000){
            time++
            if(time == 5) startActivity(goback)
        }

    }
}