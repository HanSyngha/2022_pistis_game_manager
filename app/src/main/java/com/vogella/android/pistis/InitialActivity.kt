package com.vogella.android.pistis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.*
import kotlinx.android.synthetic.main.initial_page.*
import android.view.*


class InitialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.initial_page)

        startbutton.setOnClickListener{
            val ans = AnswerText.text.toString()
            val target_time = TargetTime.text.toString()
            val game_starter = Intent(this,GameActivity::class.java)
            game_starter.putExtra("ans",ans)
            game_starter.putExtra("target_time",target_time)
            startActivity(game_starter)

        }


    }
}
