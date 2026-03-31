package com.example.mydiceapplicationxml

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val dice1 = findViewById<ImageView>(R.id.dice1)
        val dice2 = findViewById<ImageView>(R.id.dice2)
        val button = findViewById<Button>(R.id.rollbtn)
        val text = findViewById<TextView>(R.id.resulttxt)

        button.setOnClickListener {
            val result1 = (1..6).random()
            val result2 = (1..6).random()

            dice1.setImageResource(setDiceImage(result1))
            dice2.setImageResource(setDiceImage(result2))

            text.visibility = View.VISIBLE

            if (result1 != 0 && result2 != 0) {
                if (result1 == result2){
                    text.setText("Selamat, anda dapat dadu double!")
                } else {
                    text.setText("Anda belum beruntung!")
                }
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}


fun setDiceImage(result: Int): Int {
    return when(result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        6 -> R.drawable.dice_6
        else -> R.drawable.dice_1
    }
}