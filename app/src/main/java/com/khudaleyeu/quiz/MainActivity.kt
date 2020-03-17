package com.khudaleyeu.quiz

import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toggleFullScreen()
        buttonStart.setOnClickListener {
                val intent = Intent(this, GameLevels::class.java)
                startActivity(intent)
        }
    }

    fun toggleFullScreen() {
        if (window.decorView.systemUiVisibility == View.SYSTEM_UI_FLAG_VISIBLE) {
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_FULLSCREEN
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        }
    }
    override fun onBackPressed(){
        var backPressedTime = System.currentTimeMillis() + 2000
        when (backPressedTime > System.currentTimeMillis()) {
            false -> super.onBackPressed()
            true -> {
                var toast : Toast = Toast.makeText(baseContext, "Press twice to exit!", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.BOTTOM, 0, getAbsDisplayHeight())
                var toastView : View = toast.view
                toastView.setBackgroundResource(R.drawable.toast_drawable);
                toast.show();
            }
        }
    }

    fun getAbsDisplayHeight():Int{
       val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels / 9
    }

}
