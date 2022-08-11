package com.vogella.android.pistis

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import java.util.concurrent.TimeUnit
import kotlin.concurrent.timer

class WrongAnswer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wrong_answer_popup)
        var time : Int = 0

        val background_sound = MediaPlayer.create(this,R.raw.wrong_sound)
        val odap_sound = MediaPlayer.create(this,R.raw.odap)
        odap_sound.setVolume(1000F,1000F)

        background_sound.start()

        var waiter = timer(period = 1000){
            time++
            if(time == 4) odap_sound.start()
            if(time == 7) {
                background_sound.stop()
                finish()
            }

        }

    }
}